# Request Flow
The following is a sequence diagram for the flow of a request from the frontend to the backend.

![request_sequence](https://github.com/user-attachments/assets/1b56eaf0-5bbe-45d0-930d-a9ac76f237b3)

- The gateway is responsible for validating the user's token, extracting the user's email from the token, and forwarding the request to the backend after adding the email as a request header.
- For simplicity, the user email is used as the unique id for users.

## Getting Token

Cognito redirects the user back with a code, a jwt has to be generated from that code.
![auth_sequence](https://github.com/user-attachments/assets/dbcd4baa-a681-4fd1-af99-a0f11d0f1351)

## Create/Remove Todo items

- Azure Functions receive the client requests and queue them in a RabbitMQ instance (hosted on [cloudamqp](https://cloudamqp.com)).
- Another Azure Functions then poll items from the queue and do database CRUD operations accordingly.
- I know this is an overengineering, since I could have dropped the queue component, and let the backend do the CRUD operations directly after receiving the client requests; **but I wanted to try out using queues, just for learning.**
- It also might have been better to use a dedicated server instead of serverless Azure Functions, but I chose it for learning as well.

# Running Locally

## Prerequisites

1. Install Java 17 and Gradle 7.5.1
2. Add the necessary application env variables (placeholders are found in application.yaml)
3. Install `Azure Functions Core Tools`

## Commands

Clean and build the project:
```
./gradlew clean
./gradlew build
```

Then package and run the Azure lambda functions:
```
./gradlew azureFunctionsPackage
./gradlew azureFunctionsRun 
```

## Testing that everything is working
To test if the app is running fine, send any request like
```
curl --location 'http://localhost:7071/api/AzureWebAdapter/todo/all' --header 'TODO-USER-EMAIL: someEmail@mail.com'
```

