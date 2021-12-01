# Коллекция запросов

# get meal id=100002

curl --location --request GET '[http://localhost:8080/topjava/rest/meals/100002](http://localhost:8080/topjava/rest/meals/100002)'

# delete meal id=100008

curl --location --request DELETE '[http://localhost:8080/topjava/rest/meals/100008](http://localhost:8080/topjava/rest/meals/100008)'

# update meal id=100003

curl --location --request PUT '[http://localhost:8080/topjava/rest/meals/100003](http://localhost:8080/topjava/rest/meals/100003)' \
--header 'Content-Type: application/json' \
--data-raw '{
"dateTime":"2020-01-31T22:00",
"description":"update description",
"calories":570
}'

# get with date-time filter

curl --location --request GET '[http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&startTime=13:00&endDate=2020-01-30&endTime=21:00](http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&startTime=13:00&endDate=2020-01-30&endTime=21:00)'

# create meal

curl --location --request POST '[http://localhost:8080/topjava/rest/meals](http://localhost:8080/topjava/rest/meals)' \
--header 'Content-Type: application/json' \
--data-raw '{
"dateTime":"2020-01-31T23:00",
"description":"new description",
"calories":970
}'

# get all auth user meals

curl --location --request GET '[http://localhost:8080/topjava/rest/meals](http://localhost:8080/topjava/rest/meals)'