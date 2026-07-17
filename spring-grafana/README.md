# Spring Grafana PoC

This project is a proof of concept that combines a Spring Boot application with a Grafana monitoring stack.

- `src/main/java`: Spring Boot service and REST controller.
- `docker`: local Docker Compose setup for Grafana, Prometheus, Loki, and Tempo.
- `src/main/resources`: application settings and logging config.

Use this repo to explore how a Java service can be monitored and visualized with Grafana and observability tools.

## Run the project

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

Start the observability stack:

```bash
docker compose -f docker/docker-compose.yml up
```

To stop Docker Compose, run:

```bash
docker compose -f docker/docker-compose.yml down
```
