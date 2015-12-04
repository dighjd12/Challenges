json.array!(@credits) do |credit|
  json.extract! credit, :id, :userId, :currLimit, :dateUpdated, :totalInterest, :principal
  json.url credit_url(credit, format: :json)
end
