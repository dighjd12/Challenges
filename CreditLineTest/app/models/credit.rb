class Credit < ActiveRecord::Base
	belongs_to :user
	has_many :transactions

	validates :userId, presence: true
	validates :currLimit, presence: true
	validates :dateUpdated, presence: true
	validates :totalInterest, presence: true
	validates :principal, presence: true

	def self.getCredit(userId)
  		where("userId = '#{userId}'").first
	end

end
