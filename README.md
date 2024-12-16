# Book Manager with Jenkins and SonarQube

This is a simple Java Spring Boot project for managing books, with a Jenkins pipeline and SonarQube integration.

## Steps to Run

1. **Build and Run Locally**:
   ```bash
   ./gradlew bootRun
   ```

2. **Build Docker Image**:
   ```bash
   docker build -t bookmanager:latest .
   ```

3. **Run in Docker**:
   ```bash
   docker run -p 8080:8080 bookmanager:latest
   ```

4. **API Endpoints**:
   - `GET /books` - Retrieve all books.
   - `POST /books` - Add a new book.
   - `DELETE /books/{id}` - Delete a book.

5. **Jenkins Pipeline**:
   Use the Jenkinsfile for continuous integration with SonarQube analysis.
