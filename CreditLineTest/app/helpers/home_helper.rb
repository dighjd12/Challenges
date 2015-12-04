module HomeHelper
    def updatePrincipal(principal, amount)
      sum = principal.to_f + amount.to_f
    end
    def updateLimit(old_limit, draw_amount)
      newLim = old_limit.to_f - draw_amount.to_f
    end
    def negate(amount)
      negAmount = -1 * amount.to_f
    end
end
