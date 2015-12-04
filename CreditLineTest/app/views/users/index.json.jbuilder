json.array!(@users) do |user|
  json.extract! user, :id, :userId, :name
  json.url user_url(user, format: :json)
end
