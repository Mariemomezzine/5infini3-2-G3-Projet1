WORKDIR /app
RUN apt-get update && apt-get install -y curl \
    && curl -u admin:Ijnuhbygv123. -O http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar
RUN apt-get remove -y curl && apt-get clean
EXPOSE 8089
CMD ["java", "-jar", "	gestion-station-ski-1.0.jar"]
