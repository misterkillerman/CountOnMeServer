# Step 1: Use Maven to build the application
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and dependencies to cache dependencies
COPY ./UserService/pom.xml ./

# Download the dependencies (this is cached unless the pom.xml changes)
RUN mvn dependency:go-offline -q

# Copy the source code
COPY ./UserService/src ./src

# Step 2: Build the application (maven clean install)
RUN mvn clean package -DskipTests -q

# Step 3: Use Tomcat image as base to deploy the WAR file
FROM tomcat:11.0-jdk17

# Copy the WAR file from the builder stage into Tomcat's webapps directory
COPY --from=builder /app/target/CountOnMeUsers-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Change Tomcat's internal port
RUN sed -i 's/port="8080"/port="8081"/' /usr/local/tomcat/conf/server.xml

# Expose the port Tomcat is running on (default: 8080)
EXPOSE 8081

# Start Tomcat server
CMD ["catalina.sh", "run"]
