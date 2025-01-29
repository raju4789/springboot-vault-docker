# Spring Boot Vault Docker

This is a demo project that demonstrates how to integrate Spring Boot with HashiCorp Vault using Docker. The project includes a Spring Boot application configured to fetch secrets from Vault and a Docker setup to run both the application and Vault.

## Features

- Spring Boot application with Vault integration.
- Dockerized setup for both the Spring Boot application and Vault.
- Example REST endpoint to fetch secrets from Vault.
- Configurable Vault setup with development mode.

## Prerequisites

- Docker and Docker Compose installed.
- Java 17 or higher.
- Maven installed (optional, for local builds).

## Project Structure

- **`Dockerfile`**: Multi-stage Docker build for the Spring Boot application.
- **`docker-compose.yml`**: Docker Compose file to run the Spring Boot application and Vault.
- **`src/main/resources/application.yml`**: Configuration file for the Spring Boot application, including Vault integration.
- **`SecretReaderController`**: REST controller to fetch secrets from Vault.
- **`pom.xml`**: Maven configuration file with dependencies for Spring Boot and Vault.

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd springboot-vault-docker
```

### 2. Run the application

1. Build the project:
   ```bash
   docker-compose build --no-cache
   ```
2. Start the services using Docker:
   ```bash
   docker-compose up -d
   
   ```

### Vault Setup

The `docker-compose.yml` file configures Vault in development mode with the following settings:

- **Root Token**: `root`
- **Address**: `http://vault:8200`
- **KV Backend**: `secret`
- **Default Context**: `kv/springboot-vault-docker`

Add below secrets

```json
{
  "APP_SECRET": "vault-secret"
}
```

This will:
- Build the Spring Boot application.
- Start the application on port `8080`.
- Start Vault on port `8200` in development mode.

### 4. Access the Application

- **Spring Boot Application**: [http://localhost:8080](http://localhost:8080)
- **Vault UI**: [http://localhost:8200](http://localhost:8200)

### 5. Test the Secret Endpoint

The application exposes a REST endpoint to fetch secrets:

```bash
curl http://localhost:8080/secret
```

By default, the secret is configured in `application.yml` as:

```yaml
app:
  secret: ${APP_SECRET:"This is a secret"}
```

You can override this by setting the `APP_SECRET` variable in vault.



### Spring Boot Configuration

The Spring Boot application is configured to use Vault via the `application.yml` file:

```yaml
spring:
  cloud:
    vault:
      uri: http://vault:8200
      token: root
      kv:
        enabled: true
        backend: secret
        default-context: "kv/springboot-vault-docker"
```

### Debugging

The application logs Vault-related operations at the `DEBUG` level. You can adjust the logging level in `application.yml`:

```yaml
logging:
  level:
    org.springframework.cloud.vault: DEBUG
```

## References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Vault Documentation](https://docs.spring.io/spring-cloud-vault/reference/)
- [HashiCorp Vault](https://www.vaultproject.io/)

## License

This project is licensed under the Apache License 2.0. See the `LICENSE` file for details.
