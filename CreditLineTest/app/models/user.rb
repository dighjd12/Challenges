class User < ActiveRecord::Base
	has_many :transactions
	has_one :credit

	validates :userId, presence: true
	validates :name, presence: true
end
