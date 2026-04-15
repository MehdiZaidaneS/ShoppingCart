# Shopping Cart Localization App

A JavaFX-based shopping cart application that supports multi-language localization (English, Finnish, Swedish, Japanese, and Arabic) with storage using MariaDB.

## Features
* **Dynamic Localization:** Switch between 5 languages in real-time.
* **RTL Support:** Automatic Right-to-Left (RTL) orientation for Arabic.
* **database:** Saves cart records and individual items to a database.
* **Dockerized:** Fully containerized environment for both the application and the database.
* **CI/CD Ready:** Includes a Jenkins pipeline for automated building, testing, and deployment.

---

## Technologies Used
* **Language:** Java 21
* **gui.gui Framework:** JavaFX
* **Build Tool:** Maven
* **database:** MariaDB
* **Containerization:** Docker
* **CI/CD:** Jenkins

---

## Prerequisites
Before you begin, ensure you have the following installed:
* [JDK 21](https://adoptium.net/temurin/releases/?version=21)
* [Maven 3.8+](https://maven.apache.org/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* An X11 Server (like xMining) if you plan to run the gui.gui inside a Docker container.

---

## Setup & Installation

### 1. database Setup (Local)
If you are running the database manually (not via Docker):
1.  Access your MySQL/MariaDB instance.
2.  Execute the script found in `init.sql` to create the database, tables, and seed the localization strings.

### 2. Configuration
The application uses environment variables for database connectivity. Default values are:
* `DB_HOST`: localhost
* `DB_PORT`: 3306 (Note: Docker Compose maps 3307 on the host to 3306 in the container).
* `DB_USER`: root
* `DB_PASSWORD`: password

### 3. Build the Project
Run the following command to compile and package the application:
```bash
mvn clean install
```

---

## Running with Docker
1.  **Start the services:**
    ```bash
    docker-compose up --build
    ```
2.  **X11 Forwarding:** Since this is a gui.gui application, ensure your X11 server is running on your host machine. The `docker-compose.yml` is configured to look for `host.docker.internal:0`.

---

## Running Locally (Development)
To run the JavaFX application directly from your IDE or terminal:

```bash
mvn javafx:run
```
*Make sure your database is running and accessible via the credentials in `database.DatabaseConnection.java`.*

---

## CI/CD Pipeline
The included `Jenkinsfile` automates the following stages:
1.  **Check:** Pulls the latest code from GitHub.
2.  **Build:** Compiles the project using Maven.
3.  **Test:** Runs JUnit tests.
4.  **Report:** Generates JaCoCo code coverage reports.
5.  **Docker:** Builds a Docker image and pushes it to Docker Hub (`mehdizaidane/shoppincart`).

---

## database Schema
The project uses three main tables:
* `localization_strings`: Stores UI text for different languages.
* `cart_records`: Stores the header information for a saved cart.
* `cart_items`: Stores the individual products linked to a cart record.

---
**Author:** [Mehdi Zaidane]