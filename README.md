# Ezy phonebook

This is a simple phonebook application that allows you to store your contacts in a database.

## Installation

1. Clone the repository
2. You can run both applications and the database using docker-compose. Each application has its own Dockerfile and docker-compose.yml file. To run the application using docker-compose, run the following command in the root directory of the projects:

(You might have to create a docker network called `ezy_network` first, which can be done with the following command: `docker network create -d bridge ezy_network`)

```bash
docker-compose up
```

3. The application will be available at http://localhost:3000
4. Alternatively, you can run the application locally. You will need to have Java 11 and Node.js installed. The instructions for running the applications locally can be found in the README files in the `phonebook-app` and `phonebook` directories.