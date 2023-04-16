# BTC Wallet Manager

BTC Wallet Manager is a Java-based project that allows you to manage Bitcoin wallets. This README file provides information on how to set up and run the project.

## Prerequisites

To run BTC Wallet Manager, you need to have the following software installed on your system:

1. Java 17: Make sure you have Java 17 or a higher version installed on your machine. You can download it from the official Java website [here](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

2. Maven: BTC Wallet Manager uses Maven as a build and dependency management tool. You can download Maven from the official Maven website [here](https://maven.apache.org/download.cgi).

## Steps to Run the Project

Follow the steps below to run BTC Wallet Manager:

1. Run Docker Compose: BTC Wallet Manager requires a PostgreSQL database to be running. You can set up the PostgreSQL environment using Docker Compose. Open a terminal window and navigate to the root folder of the project. Run the following command to start the PostgreSQL container:

```bash
docker-compose up
```

This will create a PostgreSQL container with the necessary configuration.

2. Create the Database: After the PostgreSQL container is up and running, you need to create a database named 'wallet_management' for BTC Wallet Manager. Open a new terminal window and run the following command:

```bash
docker exec -it btc-wallet-manager createdb -U postgres wallet_management
```

This will create the 'wallet_management' database in the running PostgreSQL container. Password to the database is 123456 (application.properties).

3. Build the Project: Once the database is set up, you can build the BTC Wallet Manager project. In the terminal, navigate to the root folder of the project and run the following command:

```bash
mvn clean install
```

This will build the project and run the unit tests.

4. Run the Spring Boot Application: After successfully building the project, you can run the BTC Wallet Manager application using the following command:

```bash
mvn spring-boot:run
```

This will start the application and make it accessible at http://localhost:8080

## Endpoints

The application exposes the following two endpoints:

1. <b>GET</b> http://localhost:8080/v1/transaction/history

This endpoint returns the history of your wallet balance at the end of each hour between two given dates. It takes the following JSON payload as a request body:

```json
{
  "startDatetime": "2019-10-05T16:00:01+00:00",
  "endDatetime": "2019-10-05T17:00:01+00:00"
}
```

1. <b>POST</b> http://localhost:8080/v1/transaction/save

This endpoint allows you to save a transaction. It takes the following JSON payload as input:

```json
{
  "datetime": "2019-10-05T18:50:05+07:00",
  "amount": 100
}
```

Please make sure to replace the base URL http://localhost:8080 with the appropriate URL of your deployed application if you are running it in a different environment.

That's it! You can now use these endpoints to interact with the BTC Wallet Manager application and manage your Bitcoin wallets.