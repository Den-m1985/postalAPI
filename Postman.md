### User
[UserController](./src/main/java/com/example/controller/UserController.java)
~~~url
GET localhost:8000/api/user/all
GET localhost:8000/api/user/ {some id}

POST localhost:8000/api/user/save
~~~
~~~json
{
    "firstName": "name1",
    "middleName": "middle name 1",
    "lastName": "last name 1"
}
~~~
### Address
[AddressController](./src/main/java/com/example/controller/AddressController.java)
~~~url
GET localhost:8000/api/address/all
GET localhost:8000/api/address/ {some id}

POST localhost:8000/api/address/save
~~~
~~~json
{
  "address": "Moscow1"
}
~~~
### PostalShipment
[PostalShipmentController](./src/main/java/com/example/controller/PostalShipmentController.java)
~~~url
GET localhost:8000/api/postalshipment/all
GET localhost:8000/api/postalshipment/ {some id}
GET localhost:8000/api/postalshipment/track/ {some id}

POST localhost:8000/api/postalshipment/save
~~~
~~~json
{
  "postalShipmentCode": "123456789452365789", 
  "properties": "properties",
  "postalStatus": "SEND",
  "postalType": "BANDEROL"
}
~~~
### PostOffice
[PostOfficeController](./src/main/java/com/example/controller/PostOfficeController.java)
~~~url
GET localhost:8000/api/postoffice/all
GET localhost:8000/api/postoffice/ {some id}

POST localhost:8000/api/postoffice/save
~~~
~~~json
{
  "properties": "properties", 
  "index": "123456",
  "name": "Post office 123"
}
~~~
