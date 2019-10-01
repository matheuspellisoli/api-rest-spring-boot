## Register person ##

* **URL**

    `/api/v1/people`

* **Method:**

    `POST`

- **Data Params**

     ```
        {
            "firstName": "Eliane",
            "lastName": "Rosângela Bernardes",
            "mobilePhone": "7999461760",
            "cpf": "70143815989"
	    }
      ```

- **Success Response:**

    - **Code:** 200<br>**Content:**
      ```
        {
            "id": 
            "firstName": "Eliane",
            "lastName": "Rosângela Bernardes",
            "mobilePhone": "7999461760",
            "cpf": "70143815989"
	    }
      ```

- **Error Response:**

    * **Code:** 422 UNPROCESSABLE ENTITY
    * **Content:** `{ "Error - Invalid Name" }`
    
    * **Code:** 422 UNPROCESSABLE ENTITY
    * **Content:** `{ "Error - Error - Invalid CPF" }`

    * **Code:** 422 UNPROCESSABLE ENTITY    
    * **Content:** `{ "Error - Invalid Mobile Phone" }`

## Get all people

* **URL**

    `/api/v1/people`

* **Method:**

    `GET`

- **Data Params**

     ```
     None
    ```

- **Success Response:**

    - **Code:** 200<br>**Content:**
      ```
       [
        {
        "id": 1,
        "firstName": "Eliane",
        "lastName": "Rosângela Bernardes",
        "mobilePhone": "7999461760",
        "cpf": "70143815989"
        },
        {
            "id": 2,
            "firstName": "Tânia",
            "lastName": "Stefany Gomes",
            "mobilePhone": "82991213354",
            "cpf": "41709325526"
        },
        {
            "id": 3,
            "firstName": "Emilly",
            "lastName": "Andreia Daiane Castro",
            "mobilePhone": "51997695050",
            "cpf": "54485821250"
        }
        ]
        ```
## Get person by Id

* **URL**

    `/api/v1/people/:id`

* **Method:**

    `GET`

- **Data Params**

     ```
     None
    ```

- **Success Response:**

    - **Code:** 200<br>**Content:**
      ```
        {
        "id": 1,
        "firstName": "Eliane",
        "lastName": "Rosângela Bernardes",
        "mobilePhone": "7999461760",
        "cpf": "70143815989"
        }
        ```
- **Error Response:**

    * **Code:** 404 NOT FOUND
    * **Content:** `{ "Error - person not found" }`
