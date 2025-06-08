For local development, you need to install Postgres16 and Redis on your machine.
After installing Postgres, you have to run pg16_hrs_consolidated.sql to initialize the databse.

To run in DEV, use:
mvn spring-boot:run -Dspring-boot.run.profiles=dev

To run in Docker, user:
docker compose up --build
docker compose up


To test, use:
mvn clean test -Dspring.profiles.active=<env>