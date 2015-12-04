class CreateTransactions < ActiveRecord::Migration
  def change
    create_table :transactions do |t|
    	drop_table :transactions
      t.date :date
      t.string :userId
      t.float :amount
      t.float :OPB

      t.timestamps null: false
    end
  end
end
