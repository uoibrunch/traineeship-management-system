# Enterprise Traineeship Management System

## 📌 Overview
The Enterprise Traineeship Management System is a multi-role MVC web application built with Spring Boot. It provides a centralized platform for university committees to manage, track, and evaluate student internships. Developed using an Agile Scrum methodology, the system ensures a clear separation of concerns by leveraging Martin Fowler's Enterprise Application Architecture (EAA) patterns and Gang of Four (GoF) design patterns. 

## 👥 Role-Based Access & Features
* **Students:** Create profiles with interests/skills, apply for open positions, and maintain a traineeship logbook.
* **Companies:** Advertise traineeship positions, track assigned students, and submit final evaluations on student performance.
* **Professors:** View assigned supervised traineeships and submit evaluations assessing both the student's performance and the company's facilities.
* **Traineeship Committee:** Search for available positions, assign students to companies, allocate supervising professors based on workload or interests, and finalize grades (Pass/Fail).

## 🧠 System Architecture & Design Patterns
* **Enterprise Application Architecture (EAA):** * **Service Layer:** Decouples core business logic from the MVC controllers.
    * **Data Mapper:** Maps relational database records to in-memory domain objects via Spring Data JPA.
    * **Template View:** Renders dynamic front-end views using Thymeleaf.
* **GoF Strategy & Parameterized Factory Patterns:** * **Student-Position Matching:** Matches students to positions using interchangeable search strategies (Location-based, Interest-based, or a Composite of both) instantiated via a Parameterized Factory.
    * **Supervisor Allocation:** Assigns professors to traineeships dynamically based on their current workload (minimum load) or by matching their research interests to the position's topics.
* **Algorithmic Matching:** The interest-based strategy calculates the alignment between a student's (or professor's) interests ($I$) and a position's topics ($T$) using the Jaccard similarity metric: $J(I, T) = \frac{|I \cap T|}{|I \cup T|}$.

## 💻 Tech Stack
* **Backend:** Java, Spring Boot, Spring Security
* **Database & ORM:** MySQL, Spring Data JPA, Hibernate
* **Frontend:** Thymeleaf, HTML/CSS
* **Testing:** JUnit, Mockito
* **Design Methodology:** Agile (Scrum), UML, CRC Cards

## 📂 Documentation & Design Models
Detailed software engineering documentation is available in the `/docs` directory of this repository, including:
* **UML Class & Architecture Diagrams** detailing the domain model and EAA layers.
* **CRC (Class-Responsibility-Collaboration) Cards** documenting object interactions and responsibilities.
* **Detailed Use Cases** derived from the project's original Agile user stories.

## ⚙️ Local Setup & Installation

1. Ensure MySQL is installed and running on your local machine.
2. Create a local database named `TraineeshipDB`.
3. Update the `src/main/resources/application.properties` file with your local database credentials. *(Note: Environment variables or safe placeholders are used to prevent sensitive data exposure).*
   ```properties
   spring.application.name=TraineeshipApp
   spring.datasource.url=jdbc:mysql://localhost:3306/TraineeshipDB?useSSL=false&serverTimezone=UTC
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
