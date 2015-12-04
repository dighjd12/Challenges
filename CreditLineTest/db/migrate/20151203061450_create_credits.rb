class CreateCredits < ActiveRecord::Migration
  def change
    create_table :credits do |t|
      t.string :userId
      t.float :currLimit
      t.date :dateUpdated
      t.float :totalInterest
      t.float :principal

      t.timestamps null: false
    end
  end
end
