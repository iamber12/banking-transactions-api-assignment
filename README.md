
# Banking Assigment

## Table of Contents
- [Prerequistes](#prerequisites)
- [Installation](#installation)
- [Features](#features)
- [Usage and Sample requests](#usage)
- [Assumptions](#assumptions)

### Prerequisites
- Java
- Maven

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/iamber12/banking-assignment.git
   cd banking-assignment
   ```

2. **Open in an IDE**
    - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
    - Ensure that Maven dependencies are resolved by refreshing the project.

3. **Run the Application**
    - Locate `BankingApplication.java` in the `src/main/java/com/brainridge/avbank` directory.
    - Right-click on the file and select **Run** (or use the **Run** button in your IDE).

## Features

- **Account Management**
  - Create and read accounts
  - Error handling for duplicate accounts based on email.

- **Transaction Processing**
  - Create transactions between accounts
  - Handles validation for insufficient funds and self-transactions.

- **Generic Error Handling**
  - Handles errors such as invalid endpoint, request parameter, account id etc.

## Usage

### API Endpoints

- **Account Endpoints**
  - `POST /accounts` - Create a new account
  - `GET /accounts/{id}` - Get account details by ID

- **Transaction Endpoints**
  - `POST /transactions` - Transfer funds from one account to another
  - `GET /transactions/{accountId}` - Retrieve Transaction history of an account by ID

### Sample Requests

#### Create Account

**Request**

```bash
POST http://localhost:8000/v1/accounts
Content-Type: application/json

{
  "ownerName": "Amber Verma",
  "email": "verma.amber8@gmail.com",
  "balance": 1000
}
```

**Response**

```json
{
  "id": 1,
  "ownerName": "Amber Verma",
  "email": "verma.amber8@gmail.com",
  "balance": 1000
}
```

#### Transfer funds from one account to another

**Request**

```bash
POST http://localhost:8080/v1/transactions
Content-Type: application/json

{
  "payerEmail": "verma.amber8@gmail.com",
  "payeeEmail": "john.doe@gmail.com",
  "amount": 450.5
}
```

**Response**

```json
{
  "transactionId": "ba06f006-8303-47f7-90a2-fa109f084cfb",
  "payerEmail": "verma.amber8@gmail.com",
  "payeeEmail": "john.doe@gmail.com",
  "amount": 450.5,
  "transactionStatus": "SUCCESS"
}
```

#### Get Transactions by Account ID

**Request**

```bash
GET http://localhost:8080/v1/transactions/{accountId}
```

**Response**

```json
[
  {
    "transactionId": "ba06f006-8303-47f7-90a2-fa109f084cfb",
    "transactionStatus": "SUCCESS",
    "type": "CREDIT",
    "amount": 450.50,
    "accountBalance": 1450.50,
    "createdAt": "2024-10-25T19:03:22.435121"
  },
  {
    "transactionId": "508fd585-c69f-4272-94a9-706e21672bc7",
    "transactionStatus": "SUCCESS",
    "type": "DEBIT",
    "amount": 120.50,
    "accountBalance": 1330.00,
    "createdAt": "2024-10-25T19:08:46.456503"
  }
]
```

### Assumptions

- Account's owner name, email address and balance (greater than 0) must be provided at the time to creation.

