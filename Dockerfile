FROM openjdk
WORKDIR /usr/src/EcommerceApplication_updated1
COPY . /usr/src/EcommerceApplication_updated1/

CMD ["java","-jar","target/EcommerceApplication_updated1-0.0.1-SNAPSHOT.jar"]