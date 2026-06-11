# Railway Deployment

This app deploys to Railway as one Dockerized Spring Boot service. The root
`Dockerfile` builds the React frontend, copies it into Spring Boot static files,
and packages the backend as one container.

## Railway setup

Create:

- One service from the GitHub repository.
- One Railway MySQL database in the same project.

Service settings:

- Repository branch: `main`
- Builder: Dockerfile
- Dockerfile path: `Dockerfile`
- Public networking: enabled
- Healthcheck path: `/health`

The checked-in `railway.json` provides the Dockerfile builder and healthcheck
settings for each deployment.

## Runtime variables

Set these variables on the app service:

```text
PORT=8080
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=false
```

Railway's MySQL service exposes these variables:

```text
MYSQLHOST
MYSQLPORT
MYSQLDATABASE
MYSQLUSER
MYSQLPASSWORD
```

Reference those MySQL variables from the app service, or set the full Spring
datasource variables manually:

```text
SPRING_DATASOURCE_URL=jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}
SPRING_DATASOURCE_USERNAME=${{MySQL.MYSQLUSER}}
SPRING_DATASOURCE_PASSWORD=${{MySQL.MYSQLPASSWORD}}
```

Use the exact MySQL service name from Railway if it is not named `MySQL`.

## Verify

After deployment, open:

```text
https://<your-railway-domain>/health
```

Then test:

```text
/products
/login
/register
```

## Seed demo data

The repository includes SQL scripts for products, categories, customers, orders,
and reviews. To load them into Railway MySQL once, set these variables on the app
service:

```text
APP_SEED_DATABASE=true
APP_RESET_DATABASE_BEFORE_SEED=true
```

Redeploy the app. After `/products` shows product data, remove those two
variables or set both to `false`, then redeploy again. Leaving reset enabled will
clear and reload the demo data on every restart.
