class HomeController < ApplicationController
  include HomeHelper
  def index
  	@credit = Credit.create(userId: '1', currLimit: '1000', dateUpdated: Date.today(), totalInterest: '0', principal: '0')
    @paybackAmount = updatePrincipal(@credit.totalInterest, @credit.principal)
  end
  def drawMoney
    #draw money from this credit
  	@drawAmount = params[:draw]
    #draw money from user 1 for simulating
    # could have an input userId and use that instead
  	@credit = Credit.getCredit('1')

    #check if the amount entered is appropriate
    if @drawAmount.to_f > @credit.currLimit
      flash[:msg] = "cannot draw that much!"
      @paybackAmount = updatePrincipal(@credit.totalInterest, @credit.principal)
      render :index
      return
    end

  	@updateAmount = updatePrincipal(@credit.principal, @drawAmount)
    @newLimit = updateLimit(@credit.currLimit, @drawAmount)

    flash[:msg] = "Success"

    #check if all the inputs are filled
    if (@transaction = Transaction.create(userId: @credit.userId, date: params[:customDate], OPB: @credit.principal, amount: @drawAmount)
.valid?)
      @credit.update(principal: @updateAmount, currLimit: @newLimit)
    else
      flash[:msg] = "fill in everything!"
    end
#    @transaction = Transaction.create(userId: @credit.userId, date: Date.current(), OPB: @credit.principal, amount: @drawAmount)
    
    @paybackAmount = updatePrincipal(@credit.totalInterest, @credit.principal)
    render :index
  end
  def payMoney
    #pay money from this credit
    @payAmount = negate(params[:pay])
    @credit = Credit.getCredit('1')

    #check the amount
    if params[:pay].to_f > @credit.principal
      flash[:msg2] = "cannot pay that much!"
      @paybackAmount = updatePrincipal(@credit.totalInterest, @credit.principal)
      render :index
      return
    end

    @updateAmount = updatePrincipal(@credit.principal, @payAmount)
    @newLimit = updateLimit(@credit.currLimit, @payAmount)

    flash[:msg2] = "Success"

    #check if the inputs are all filled
    if (@transaction = Transaction.create(userId: @credit.userId, date: params[:customDate], OPB: @credit.principal, amount: @payAmount)
.valid?)
      @credit.update(principal: @updateAmount, currLimit: @newLimit)
     # @transaction = Transaction.create(userId: @credit.userId, date: params[:customDate], OPB: @credit.principal, amount: @payAmount)
    else
      flash[:msg2] = "fill in everything"
    end

    @paybackAmount = updatePrincipal(@credit.totalInterest, @credit.principal)
    render :index

  end
  def calcInterest
    #calculates interest, assuming that current date is day 30
    @APR = 0.35
    @credit = Credit.getCredit('1')

    #for simulating, we calculate 'the current date',
    # but this should really be an input to the function
    @currentDate = @credit.dateUpdated + 30.days
    @transactions_this_month = Transaction.getTransactions(@credit.dateUpdated, @currentDate, @credit.userId)
 
    #traverse through the list of transactions happened this month, and calculate interest
    @s = 0
    @transactions_this_month.each do |element|
        if @lastDate == nil
          @numDays = (element.date-@credit.dateUpdated).to_i
        else
          @numDays = (element.date-@lastDate).to_i
        end

        @m = element.OPB.to_f * @APR / 365 * @numDays
        @s = @s + @m
        @lastDate = element.date
    end
    #finish calculating
    if @lastDate == nil
      @m = @credit.principal.to_f * @APR / 365 * 30
      @s = @s + @m
    else
      @numDays = (@currentDate-@lastDate).to_i
      @m = @credit.principal.to_f * @APR / 365 * @numDays
      @s = @s + @m
    end


    @interest = @s + @credit.totalInterest
    #@credit.update(dateUpdated: @currentDate, totalInterest: @interest)
    #for simulating purpose, don't update the date
    @credit.update(totalInterest: @interest)

    @paybackAmount = updatePrincipal(@credit.totalInterest, @credit.principal)
    render :index
  end
end
