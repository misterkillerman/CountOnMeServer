# Use the official Tomcat image as base
FROM tomcat:11.0-jdk17

# Copy the WAR file into the Tomcat webapps directory
COPY target/CountOnMe-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port Tomcat is running on (default: 8080)
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]