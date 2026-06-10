# Northflank Deployment

This project deploys to Northflank as one Dockerized Spring Boot combined service with a Northflank MySQL addon.

Northflank combined services build from a Git branch and automatically deploy new commits when CI and CD are enabled. The root `Dockerfile` builds the React app, copies it into Spring Boot static files, and packages the backend as one container.

## Northflank services

Create:

- One **MySQL addon**.
- One **combined service** connected to this GitHub repository.

Combined service settings:

- Repository branch: `main`
- Build type: `Dockerfile`
- Dockerfile path: `/Dockerfile`
- Build context: `/`
- Port: `8080`
- Public HTTP port: enabled
- Health check path: `/health`
- CI: enabled
- CD: enabled

Northflank should detect `EXPOSE 8080` from the Dockerfile. If it does not, add port `8080` manually and expose it publicly.

## Runtime variables

Set these variables on the combined service or in a secret group inherited by the service:

```text
PORT=8080
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=false
```

For MySQL, use either option.

Option A: set the full Spring datasource variables manually from the MySQL addon's internal connection details:

```text
SPRING_DATASOURCE_URL=jdbc:mysql://<mysql-internal-host>:<mysql-port>/<mysql-database>
SPRING_DATASOURCE_USERNAME=<mysql-user>
SPRING_DATASOURCE_PASSWORD=<mysql-password>
```

Option B: link the MySQL addon to a secret group and alias the addon variables to:

```text
MYSQL_HOST
MYSQL_PORT
MYSQL_DATABASE
MYSQL_USERNAME
MYSQL_PASSWORD
```

The app will compose `SPRING_DATASOURCE_URL` automatically from those aliases if the full Spring datasource URL is not set.

## GitHub secrets

Northflank handles deployment from Git directly, so no deployment token is required for the default setup.

Optionally set this repository secret so GitHub Actions can smoke-test the deployed app:

```text
NORTHFLANK_APP_URL
```

Use the public Northflank service URL without a trailing slash, for example:

```text
https://shopping-cart--main--service-name.<region>.northflank.app
```

## CI/CD evidence

After pushing to `main`, capture:

- The green GitHub Actions test/build run.
- The Northflank combined service build log.
- The Northflank deployment log showing the deployed commit.
- The public `/health` endpoint.
- The `/products`, `/login`, cart, favourites, and registration pages.

## References

- Northflank combined services automatically build and deploy the tracked Git branch when CI/CD is enabled.
- Northflank supports Dockerfile builds from the repository root.
- Northflank MySQL addons can be linked to secret groups and exposed to workloads as runtime variables.
