# Hospital Management System

Spring Boot REST API for managing hospital operations including patients, doctors, appointments, and billing.

## About The Project

A comprehensive Hospital Management System that handles Patient profiles, Doctor profiles, Appointment scheduling, 
and Billing & Payment management with proper validation, exception handling, and API documentation.


## Features
- Patient Profile Management
- Doctor Profile Management
- Appointment Scheduling
- Billing & Payment Management
- Global Exception Handling
- Validation
- Interactive Swagger UI documentation

## Tech Stack
- Java 21
- Spring Boot
- MySQL
- JPA/Hibernate
- Lombok
- Swagger UI

## Architecture
- REST API
- Adapter Design Pattern
- DTO Pattern
- Soft Delete Pattern (Status Management)
- Repository pattern
- Layered Architecture (Controller → Service → Repository)

## API Endpoints

### Patient APIs
| Method | URL                           | Description        |
|--------|-------------------------------|--------------------|
| POST   | /api/patients                 | Create patient     |
| GET    | /api/patients                 | Get all patients   |
| GET    | /api/patients/{id}            | Get patient by id  |
| PATCH  | /api/patients/{id}            | Update patient     |
| DELETE | /api/patients/{id}/deactivate | Deactivate patient |
| PUT    | /api/patients/{id}/activate   | Activate patient   |

### Doctor APIs
| Method | URL                          | Description       |
|--------|------------------------------|-------------------|
| POST   | /api/doctors                 | Create doctor     |
| GET    | /api/doctors                 | Get all doctors   |
| GET    | /api/doctors/{id}            | Get doctor by id  |
| PATCH  | /api/doctors/{id}            | Update doctor     |
| DELETE | /api/doctors/{id}/deactivate | Deactivate doctor |
| PUT    | /api/doctors/{id}/activate   | Activate doctor   |

### Appointment APIs
| Method | URL                               | Description          |
|--------|-----------------------------------|----------------------|
| POST   | /api/appointments                 | Create appointment   |
| GET    | /api/appointments                 | Get all appointments |
| GET    | /api/appointments/{id}            | Get by id            |
| GET    | /api/appointments/patient/{id}    | Get by patient       |
| GET    | /api/appointments/doctor/{id}     | Get by doctor        |
| PATCH  | /api/appointments/{id}/reschedule | Reschedule           |
| DELETE | /api/appointments/{id}/cancel     | Cancel               |
| PUT    | /api/appointments/{id}/complete   | Complete             |

### Billing APIs
| Method | URL                           | Description         |
|--------|-------------------------------|---------------------|
| POST   | /api/billing                  | Create billing      |
| GET    | /api/billing                  | Get all billings    |
| GET    | /api/billing/{id}             | Get by id           |
| GET    | /api/billing/appointment/{id} | Get by appointment  |
| PUT    | /api/billing/{id}/pay         | Mark as paid        |
| PUT    | /api/billing/{id}/pending     | Mark as pending     |
| GET    | /api/billing/unpaid           | Get unpaid billings |
| GET    | /api/billing/paid             | Get paid billings   |

## How to Run

1. Clone the repository
2. Create MySQL database: hospital_db
3. Update application.properties with DB username/password
4. Run the project
5. Access Swagger UI
http://localhost:8080/swagger-ui/index.html

## Future Enhancements
- JWT Authentication
- Role-based Access Control
- Email Notifications
- Search & Filter
- Pagination

## Author

Navneet Indora
Email: navneet.indora1@gmail.com