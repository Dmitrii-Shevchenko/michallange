String Iterator locates in Iterator directory.

Modelling CRUD with validation:

Create:
curl -XPOST -H "Content-type: application/json" -d '{"firstName":"John","lastName":"Doe,"age":"19" ,"number": [ "89997775566", "89997775567", "89997775568" ], "email":["johndoe@gmail.com"]}' 'http://localhost:8080/users'

Read:
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/users'

Update:
curl -XPUT -H "Content-type: application/json" -d '{"firstName":"John","lastName":"Doe,"age":"19" ,"number": [ "89997775566", "89997775567", "89997775568" ], "email":["johndoe@gmail.com"]}' 'http://localhost:8080/users?id=[UserId]'

Delete:
curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/users?id=[UserId]'
