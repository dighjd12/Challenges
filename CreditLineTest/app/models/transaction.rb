class Transaction < ActiveRecord::Base

	validates :userId, presence: true
	validates :date, presence: true
	validates :OPB, presence: true
	validates :amount, presence: true

	def self.getTransactions(last_calculated_date, current_date, userId)
		where("userId = '#{userId}' AND date >= '#{last_calculated_date}' AND date <= '#{current_date}'").order(:date)
	end
end
