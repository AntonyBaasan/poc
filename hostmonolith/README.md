# Host Monolith

## Running the Application

### Backend (Spring Boot with Gradle)

To run the backend Spring Boot application:

```bash
cd backend
./gradlew bootRun
```

The application will start on `http://localhost:8080` by default.

Alternatively, to build and run the JAR:

```bash
cd backend
./gradlew build
java -jar build/libs/*.jar
```
