# rookies-nashtech-ecommerce-assignment


# Mục lục
## [Database](#database)

## Ecommerce Project
### How to test this?


1. [Install Docker Compose](https://docs.docker.com/compose/install/)
2. Open docker desktop
3. Clone this repository
4. Move to folder backend-springboot (location contain file docker-compose.yml)
5. delete file mvnw, mvnw.cmd and run script `mvn -N io.takari:maven:0.7.7:wrapper` in terminal
6. Open terminal then run all containers with `docker-compose up`
7. Verify in Datadog that your container picks up the docker and logs of project
8. Open favorite brower and test Application (App running on port 3000, backend running on port 8080 )
9. Account Admin (username: ltv18745, password: 123456)

### Diagram
![image](https://user-images.githubusercontent.com/104447131/197401130-56f47791-6d15-414c-97eb-6847487d56de.png)

