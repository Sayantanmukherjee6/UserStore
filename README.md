# Objective
Getting intuition about how to interact with drools rest api using Kotlin.

## Dependencies
* Java 11
* jBPM Docker image: https://www.jbpm.org/learn/gettingStartedUsingDocker.html

## Steps for creating rules to run this demo
* Install Drool Workbench along with Kie Server (Link provided above).
* Login with Business Central UI (http://localhost:8080/business-central/)
* By Default there will be space named **MySpace**. Inside that create a project **userstore**.
* Inside project click on **Import Asset**. Upload all required assets, provided along with this repo.
* Once done Asset page will look like this:
* ![decision-boundary](https://github.com/Sayantanmukherjee6/Neural-Network-Decision-Boundary-Using-Keras/blob/master/decision_boundary.gif)
* One sample rule:
* ![decision-boundary](https://github.com/Sayantanmukherjee6/Neural-Network-Decision-Boundary-Using-Keras/blob/master/decision_boundary.gif)

## Deployment  
* Deploy the project. On top right side there is a **Deploy** option.
* Once deployed, from **Menu** on top navigate to **Execution Servers** to check the status.
* Once everything is good, the deployment page will look like this:
* ![decision-boundary](https://github.com/Sayantanmukherjee6/Neural-Network-Decision-Boundary-Using-Keras/blob/master/decision_boundary.gif)
* Once deployed **container-id** is needed. Use postman/curl. For reference check below:
* ![decision-boundary](https://github.com/Sayantanmukherjee6/Neural-Network-Decision-Boundary-Using-Keras/blob/master/decision_boundary.gif)
* Run the Kotlin spring-boot project with correct container-id (change in application.yml is required).

## Rules and corresponding curl Commands for verification:
* Checks whether user can purchase based on user status.
* If User.status = true, then User.canPurchase= true else User.canPurchase= false
```curl
curl --location --request POST 'http://localhost:8081/rule/user-status' \
--header 'Content-Type: application/json' \
--data-raw '{ 
    "user": {    
        "age": 5,
        "canPurchase": true,
        "status": false,
        "userName": "abcd"
    },
    "store": {
     }
}'
```

* Checks whether Store has adequate amount of item.
* If Store.quantity < 1, then Store.saleNotAllowed= true else Store.saleNotAllowed= false
```curl
curl --location --request POST 'http://localhost:8081/rule/item-quantity-status' \
--header 'Content-Type: application/json' \
--data-raw '{ 
    "user": {
    },
    "store": {
        "itemName": "EFGH",
        "itemType": "pencil",
        "price": 10,
        "quantity": 0,
        "saleNotAllowed": false
    }
}'
```

* Checks whether User can buy item from store based on age.
* If User.age < 18 and Store.itemType = **alcohol**, then user.canPurchase = false else user.canPurchase = true
```curl
curl --location --request POST 'http://localhost:8081/rule/age-based-purchase' \
--header 'Content-Type: application/json' \
--data-raw '{ 
    "user": {    
        "age": 5,
        "canPurchase": true,
        "status": false,
        "userName": "abcd"
    },
    "store": {
        "itemName": "MNOP",
        "itemType": "alcohol",
        "price": 100,
        "quantity": 27,
        "saleNotAllowed": false
    }
}'
```
