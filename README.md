## Simple Web Application

- [API Documentation](https://simpleappreactspring.stoplight.io/docs/simple-web-application/af776cba49937-user-database)
- [Client Repository](https://github.com/notsaki/simple-web-application-client)


- Clean build for production: `make build`

- Run for production: `make run`

### General information

The application supports basic user management with admin authentication using sessions. An admin can view, save, 
update and delete users.

The application can be run through a docker container (a compose file is also provided) and the configuration is being 
done through environment variables. The `.env.example` should be renamed to `.env` with all the required information 
changed.

- Before building the app, a production build of the client should be put into `/resources/static` directory (`bundle.js` 
and `index.html` files).
- If the app is run in development mode, the application.properties file will be used for configuration. Inside the 
container, this file is being deleted.
- The dev environment uses an H2 database instead of a MySQL.

### Requirements

- Java 17.
- Node JS and Yarn for the client app.
- Makefile to run the scripts.
- Docker and Docker Compose.

### Environment variables
- `MYSQLDB_USER` the admin database user.
- `MYSQLDB_ROOT_PASSWORD` the admin database password.
- `MYSQLDB_DATABASE` the database name that is going to be used.
- `MYSQLDB_LOCAL_PORT` the exposed database port.
- `MYSQLDB_DOCKER_PORT` the database's container port that is not exposed outside of docker.
- `SPRING_LOCAL_PORT` server port that is exposed.
- `SPRING_DOCKER_PORT` the server's container port that is not exposed outside of docker.
- `ADMIN_USERNAME` the server's default admin user. This admin is used to retrieve an access token. 
- `ADMIN_PASSWORD` the server's admin password. Recommended minimum length: 8 characters.
- `ALLOWED_ORIGIN` client's address that is going to be sent through headers (for CORS).

### Makefile Scripts

- `build` build the container.
- `run` run the application.
- `deploy` clone the client, build and run whole the application.

### Deploy

Simply run the `deploy.sh` script.