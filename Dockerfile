FROM tomcat:9.0-jdk21-temurin

# Expose port
EXPOSE 8082

# Remove default Tomcat ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Deploy your WAR file as ROOT
COPY target/petclinic.war /usr/local/tomcat/webapps/ROOT.war

# Start Tomcat server
CMD ["catalina.sh", "run"]

# # FROM eclipse-temurin:21-jdk
# EXPOSE 8082
# ADD target/petclinic.war petclinic.war
# ENTRYPOINT ["java","-jar","/petclinic.war"]

