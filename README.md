## 🎥 Project Demonstration Videos

- **🔹 User Functionalities Overview**  
  [Watch Video on Loom](https://www.loom.com/share/650d936d0d7d4402aa63e12b046b9945?sid=0be87d46-69d0-40ef-80b9-29b93c2a4abc)

- **🔸 Admin Functionalities Overview**  
  [Watch Video on Loom](https://www.loom.com/share/1188c8276db24e84bf009563e8c6837a?sid=5e83cf21-c6d4-41e0-9747-32a7a6c3da8a)


# StayNova - Hotel Booking System

StayNova is a hotel booking web application built with **Spring Boot** for the backend and **React** for the frontend. It allows users to view available rooms, book rooms, and manage their bookings. The application also supports user authentication, profile management, and administrative functionality to manage rooms, users, and bookings.

## Tech Stack

- **Backend**: Spring Boot (Java)
- **Frontend**: React.js
- **Database**: MySQL
- **Cloud Storage**: AWS S3 for storing room images
- **Security**: JWT Authentication
- **Build Tool**: Maven
- **API Requests**: Axios for frontend API calls

## Project Setup

### Prerequisites

1. **Java** (version 8 or later) for backend development.
2. **Node.js** and **npm** (Node Package Manager) for frontend development.
3. **MySQL** for database management.
4. **AWS Account** (for AWS S3).

### Backend Setup

1. Clone the repository and navigate to the `backend` folder.
2. Install required dependencies:
    ```bash
    mvn clean install
    ```

3. **Database Configuration**:
   - MySQL database is used for storing room and booking information.
   - **Database URL**: `jdbc:mysql://localhost:3306/hotel-booking`
     
4. Configure **AWS S3** to handle image uploads:
   - **AWS S3 Bucket Name**: `hotel-images-proj`(provided in application properties of this folder)

5. **Spring Boot Configuration**:
   Update the `application.properties` file with the following configuration:

    ```properties
    spring.application.name=HotelBooking
    spring.datasource.url=jdbc:mysql://localhost:3306/hotel-booking
    spring.datasource.username= //your db username
    spring.datasource.password= //your db password
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    aws.s3.secret.key=//your aws s3 secret key
    aws.s3.access.key=// your aws s3 access key
    aws.s3.bucket-name=hotel-images-proj

    spring.servlet.multipart.max-file-size=100MB
    spring.servlet.multipart.max-request-size=100MB
    ```

6. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

### Frontend Setup

1. Navigate to the `frontend` folder.
2. Install frontend dependencies:
    ```bash
    npm install
    ```

3. Start the React application:
    ```bash
    npm start
    ```

### Backend Overview

The backend exposes various REST APIs for handling user authentication, room management, and bookings. Below is an overview of the key APIs available:

#### **Authentication**

- **Register User**: 
    - `POST /auth/register`
    - Registers a new user with their details.

- **Login User**: 
    - `POST /auth/login`
    - Logs in a registered user and returns a JWT token for session management.

#### **Users**

- **Get All Users**: 
    - `GET /users/all`
    - Retrieves all registered users.

- **Get User Profile**: 
    - `GET /users/get-logged-in-profile-info`
    - Retrieves the profile of the logged-in user.

- **Get User by ID**: 
    - `GET /users/get-by-id/{userId}`
    - Retrieves a single user's profile by ID.

- **Delete User**: 
    - `DELETE /users/delete/{userId}`
    - Deletes a user from the system.

#### **Rooms**

- **Add Room**: 
    - `POST /rooms/add`
    - Adds a new room to the database.

- **Get All Available Rooms**: 
    - `GET /rooms/all-available-rooms`
    - Retrieves all available rooms.

- **Get Available Rooms by Date and Type**: 
    - `GET /rooms/available-rooms-by-date-and-type?checkInDate={checkInDate}&checkOutDate={checkOutDate}&roomType={roomType}`
    - Retrieves available rooms based on dates and room type.

- **Get Room Types**: 
    - `GET /rooms/types`
    - Retrieves all available room types.

- **Get Room by ID**: 
    - `GET /rooms/room-by-id/{roomId}`
    - Retrieves a specific room by its ID.

- **Delete Room**: 
    - `DELETE /rooms/delete/{roomId}`
    - Deletes a room by its ID.

- **Update Room**: 
    - `PUT /rooms/update/{roomId}`
    - Updates room details.

#### **Bookings**

- **Book Room**: 
    - `POST /bookings/book-room/{roomId}/{userId}`
    - Books a room for a user.

- **Get All Bookings**: 
    - `GET /bookings/all`
    - Retrieves all bookings.

- **Get Booking by Confirmation Code**: 
    - `GET /bookings/get-by-confirmation-code/{bookingCode}`
    - Retrieves a booking using the confirmation code.

- **Cancel Booking**: 
    - `DELETE /bookings/cancel/{bookingId}`
    - Cancels a user's booking.

### AWS S3 Integration for Room Images

The application uses AWS S3 for storing room images. When a user adds a room to the system, the room image is uploaded to the S3 bucket (`hotel-images-proj`). The backend uses the AWS SDK to handle the image upload process.

### Security

JWT Authentication is used to secure the application. Upon successful login, users receive a JWT token that must be included in the header of each request for authentication. The `Authorization` header is used to send the token in the following format:

### Frontend Overview

The frontend of the StayNova Hotel Booking System is built with **React.js**, providing a responsive and dynamic user interface. Here’s an overview of the key features and components:

- **User Authentication**: The user can register, log in, and manage their profile. The authentication flow relies on JWT tokens to authenticate the user’s sessions.
  
- **Room Management**: Users can view available rooms, check availability based on dates, and filter rooms by type.
  
- **Booking System**: Users can book available rooms. The booking functionality also allows users to cancel or view their existing bookings.

- **Admin Dashboard**: For users with an **Admin** role, the system provides functionality to manage rooms, users, and bookings. Admins can add, update, or delete rooms and also view a list of all users and their bookings.

#### Key Components of the Frontend:

1. **Login/Register Page**:
   - Forms for user login and registration.
   - Sends API requests to the backend for authentication and registration.

2. **Dashboard**:
   - After logging in, users are directed to the dashboard where they can see available rooms, their profile, and current bookings.
   - Admins have access to additional management functionalities.

3. **Room Listings**:
   - Displays a list of rooms with filters such as room type, check-in date, and check-out date.
   - Each room is shown with details and an image (stored in AWS S3).

4. **Booking Page**:
   - Allows users to make room bookings based on selected dates.
   - Sends booking data to the backend API to confirm the booking.

5. **Profile Management**:
   - Users can view and update their profile information.

6. **Admin Panel** (for admin users):
   - Allows admins to manage rooms, users, and bookings. Admins can add or remove rooms, update room details, and view user bookings.
  
## Future Enhancements

- **Real-Time Gateway Integration**: Implement a **real-time payment gateway** integration for seamless booking and payment processing. This will provide users with instant feedback on payment success or failure, enhancing user experience during booking.

- **Concurrency and Multi-User Handling**: Enhance the backend to handle **high concurrency** with **thread-safe operations** and improved **database transaction management**. This will ensure the system performs well under heavy traffic, especially during peak booking times.

- **Real-Time Availability Updates**: Introduce **real-time room availability** updates using **WebSockets** or **Server-Sent Events (SSE)**, allowing users to see the most up-to-date room status without having to refresh the page.

- **Admin Panel Enhancements**: Expand the **Admin Panel** to include **real-time analytics** and **live updates** on bookings, revenue, and user activities, improving decision-making capabilities for hotel management.

- **User Activity Tracking and Personalization**: Incorporate a **user activity tracking system** to personalize the experience. Use real-time data to recommend rooms, promotions, and deals based on user behavior and preferences.

These future enhancements will help improve system performance, scalability, and the overall user experience.


