## 📘 API Test Automation (TestNG + RestAssured)
### 📌 Project Overview
This project is an API automation test suite built using:
- Java
- TestNG
- RestAssured

It tests CRUD operations (Create, Read, Update, Delete) for User API from:
```url
https://gorest.co.in/public/v2
```

### ⚙️ Prerequisites
Make sure you have the following installed:
- Java JDK 8+
- Maven
- IDE (IntelliJ IDEA / Eclipse)

### 🔐 Configuration
#### 1. Base URL

Defined in: `BaseTest.java`

#### 2. Access Token (Important ⚠️)
Update your token in: `UserClient.java`
```java
private final String accessToken = "YOUR_ACCESS_TOKEN";
```
👉 To get token, please log in from: https://gorest.co.in/

---
### 🚀 How to Run Tests
#### ▶️ Option 1: Run via TestNG XML from IDE

Run this file: `testng.xml`

#### ▶️ Option 2: Run via Maven
```bash
mvn test
```
or
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---
### 🧪 Test Cases Covered
#### 1. ✅ Get Users
- Verify get user success
- Validate response structure
- Count active users

#### 2. ✅ Create User
- Verify create user success
- Validate response matches request
- Able to get created user by ID and response matched 

#### 3. ✅ Update User
- Verify Update user's info success
- Validate response matches request
- - Able to get created user by status and response matched

#### 4. ✅ Delete User
- Verify Delete user success → 204
- Verify Delete unsuccess (not found) → 404

---
### 📦 Key Components
#### 1. BaseTest
- Setup base URI
#### 2. UserClient
- Handles all API requests (GET, POST, PUT, DELETE)
#### 3. UserModel
- Request payload model
#### 4. UserAssertion
- Custom reusable assertions
#### 5. ApiTest
- Main test cases