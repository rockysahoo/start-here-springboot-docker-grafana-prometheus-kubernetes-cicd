## This is a test data file, it doesn't contain any relevant information. It is only used for testing purposes.

---

### Endpoints : MyFirstController

- POST : http://localhost:8081/app/v1/add-name
  - Request Body:
    ~~~ json
    {
        "name": "Rocky"
    }
    ~~~

~~~ curl
curl --location 'http://localhost:8081/app/v1/add-name' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Rocky"
}'
~~~

- GET : http://localhost:8081/app/v1/get-names

  - Response:
    ~~~ json
    {
      "data": {
        "808b34b4-23e3-4a09-a1e7-207c194346ad": "Sima",
        "82548a06-eb93-4671-95a4-e1fa2309667e": "Pratik"
      },
      "message": "Names retrieved successfully"
    }
    ~~~

~~~ curl
curl --location 'localhost:8081/app/v1/get-name'
~~~


### Endpoints : MyDataController

- POST : http://localhost:8081/app/v2/products
  - Request Body : 
  ```json
  {
    "name": "Product 1",
    "price": 100.0
  }
  ```

- GET : http://localhost:8081/app/v2/products
- DELETE : http://localhost:8081/app/v2/products/{id} 

---
- POST : http://localhost:8081/app/v2/mobiles
  - Request Body : 
  ```json
  {
    "brand": "IPhone",
    "model": 500.0
  }
  ```
- GET : http://localhost:8081/app/v2/mobiles
- DELETE : http://localhost:8081/app/v2/mobiles/{id}

