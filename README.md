# 🚆 Ticket Booking System

A Spring Boot based Railway Ticket Booking System that simulates real-world train ticket booking operations with proper layered architecture, seat validation, and global exception handling.

---

## 📌 Project Overview

This system allows:

- User registration
- Train management
- Train schedule management
- Ticket booking with multiple passengers
- Seat availability validation
- Centralized exception handling

The architecture follows clean separation of concerns:

Controller → Service → Repository → Database

---

## 🏗️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- REST APIs
- Global Exception Handling

---

## 📂 Project Structure

```text
com.ticketbooking.app
│
├── controller
│   ├── UserController.java
│   ├── TrainController.java
│   └── BookingController.java
│
├── service
│   ├── UserService.java
│   ├── TrainService.java
│   └── BookingService.java
│
├── repository
│   ├── UserRepository.java
│   ├── TrainRepository.java
│   ├── ScheduleRepository.java
│   ├── BookingRepository.java
│   └── PassengerRepository.java
│
├── model
│   ├── User.java
│   ├── Train.java
│   ├── TrainSchedule.java
│   ├── Booking.java
│   └── Passenger.java
│
└── exceptions
    ├── GlobalExceptionHandler.java
    ├── ResourceNotFoundException.java
    └── SeatNotAvailableException.java
```


# 🧠 System Architecture

## 🔄 Layered Architecture

Client
↓
Controller Layer
↓
Service Layer (Business Logic)
↓
Repository Layer (JPA)
↓
Database (MySQL)


Each layer has a clear responsibility:

- Controller → Handles HTTP requests
- Service → Business logic & validation
- Repository → Database interaction
- Model → Entity mapping
- Exceptions → Centralized error handling

---

# 🔗 Entity Relationships

### 👤 User
- One user can have multiple bookings

### 🚆 Train
- One train can have multiple schedules

### 📅 TrainSchedule
- One schedule belongs to one train
- One schedule can have multiple bookings
- Tracks available seats per journey date

### 🎫 Booking
- Belongs to one user
- Belongs to one schedule
- Can contain multiple passengers

### 🧍 Passenger
- Belongs to one booking

---






## 📊 Data Flow Diagram

![Data Flow Diagram](docs/DataFlow.png)

# 🎫 Complete Booking Data Flow

## Step-by-Step Flow

1. Client sends booking request:
   - userId
   - scheduleId
   - passenger list

2. BookingController receives request

3. BookingService performs:
   - Fetch User (UserRepository)
   - Fetch TrainSchedule (ScheduleRepository)
   - Validate seat availability
   - If seats insufficient → throw SeatNotAvailableException
   - Deduct available seats
   - Create Booking
   - Save passengers
   - Persist booking

4. BookingRepository saves data

5. Response returned to client

---

# 🛡️ Exception Handling

## 1️⃣ ResourceNotFoundException
Thrown when:
- User not found
- Train not found
- Schedule not found
- Booking not found

## 2️⃣ SeatNotAvailableException
Thrown when:
- Requested seats > available seats

## 3️⃣ GlobalExceptionHandler
- Handles all exceptions centrally
- Returns proper HTTP status codes
- Avoids exposing internal stack traces
- Standardizes API error responses

---

# 🗄️ Database Tables

- users
- train
- train_schedule
- booking
- passenger

Hibernate automatically manages table creation using:


---

# 📌 Core Business Rules

### Seat Validation Rule

Before booking:

if (availableSeats < requestedSeats)
throw SeatNotAvailableException
else
reduce availableSeats
save booking


This ensures data integrity and prevents overbooking.

---

# 🔥 Features Implemented

✔ Clean layered architecture  
✔ Entity relationship mapping  
✔ One-to-many & many-to-one relationships  
✔ Seat validation logic  
✔ Centralized exception handling  
✔ Modular package structure  
✔ Service-level business logic separation  
✔ Proper database normalization  

---

# 🚀 API Endpoints (Planned / Implemented)

## 👤 User APIs
- POST /users
- GET /users/{id}

## 🚆 Train APIs
- POST /trains
- GET /trains
- GET /trains/{id}

## 📅 Schedule APIs
- POST /schedules
- GET /schedules
- GET /schedules/{id}

## 🎫 Booking APIs
- POST /bookings
- GET /bookings/{id}
- GET /users/{id}/bookings

---

# 📈 Learning Outcomes

By building this project, you gain understanding of:

- JPA relationship mapping
- Entity lifecycle management
- Business logic implementation
- Service layer design
- Exception-driven development
- Data validation before persistence
- Real-world booking flow design
- Clean architecture principles

---

# 🔮 Future Enhancements

### Phase 2
- JWT Authentication
- Role-based access (Admin/User)
- BCrypt password encryption

### Phase 3
- Payment gateway integration
- Booking cancellation with seat restoration
- Waiting list system

### Phase 4
- Transaction management
- Concurrency control (prevent double booking)
- Optimistic/Pessimistic locking

### Phase 5
- Redis caching
- Pagination and filtering
- Swagger API documentation
- Logging with AOP

---

# 🧑‍💻 How to Run

1. Clone the repository
2. Create MySQL database:


ticket_booking_system


3. Configure application.properties
4. Run Spring Boot application
5. Test APIs using Postman

---

# 💎 Why This Project Is Strong

This project demonstrates:

- Real-world system modeling
- Clean architecture practices
- Scalable design
- Strong backend fundamentals
- Production-ready structural thinking

---

# 👨‍💻 Author

TicketBookingSystem – Spring Boot Backend Project



