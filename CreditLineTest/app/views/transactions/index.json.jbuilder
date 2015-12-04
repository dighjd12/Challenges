json.array!(@transactions) do |transaction|
  json.extract! transaction, :id, :date, :userId, :amount, :OPB
  json.url transaction_url(transaction, format: :json)
end
