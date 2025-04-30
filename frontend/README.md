# Spring Boot + Vue 3 + Vite Project

This project is a full-stack web application using Spring Boot for the backend and Vue 3 with Vite for the frontend.

🧱 Platform & Tools
Apache – Used as the HTTP web server to host the backend application.

MySQL – Used as the relational database for storing application data.


📥 How to Import / Clone This Project
You can clone this repository to your local machine using Git:

🔹 Using HTTPS:
git clone https://github.com/capable1999/student.git



## 🧰 Tech Stack

- **Backend**: Spring Boot (Java)
- **Frontend**: Vue 3 + Vite
- **Build Tools**: Maven (for backend), Vite (for frontend)
- **Communication**: REST API using Axios (JSON)

### 🔙 Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL Driver
- Maven 

### 🔜 Frontend
- Vue 3
- Vite
- Vue Router
- Axios
- JavaScript (ES6+)
- HTML & CSS

---

Before that, you need to import student_management.sql into your database tool.

1. Backend Setup (Spring Boot)
cd student
./mvnw spring-boot:run or mvn spring-boot:run


2. Frontend Setup (Vue 3 + Vite)
cd frontend
npm install
npm run dev

3. Build for Production
-Frontend:
npm run build

-Copy the built files from frontend/dist into backend/src/main/resources/static/ for Spring Boot to serve them.

-Backend:
mvn install clean
mvn spring-boot:run

or 

mvn clean package
java -jar target/student-0.0.1-SNAPSHOT.jar


NOTE : Run frontend and backend at the same time (on two different terminal).

📖 Usage
1. Student List
Navigate to the Home page where a list of students will be displayed.

You can view the details of each student by clicking on the "View" icon button next to their name.

You can also edit a student's information by clicking the "Edit" icon button.

2. Add a Student
Go to the Student Form page by clicking on the "Create Student" button on the home page.

Fill in the student details such as student ID, name, email, , etc.

Click Create Student to save the new student to the database.

3. Edit Student Information
From the Student List page, click the "Edit" icon button for the student you want to update.

Update the student information as needed, then click Update to update the record in the database.

4. View Student Details
To see detailed information about a student, click on the View button next to their name in the Student List. This will show student details.

5. Delete a Student
In the Student List page, click the "Delete" icon button to remove a student from the database.

6. Search a Student
In the Student List page, click the Search bar and write specific Student ID, Name or email to find a specific student.

MIT License

Copyright (c) 2025 Mohd Ikmal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
