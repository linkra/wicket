# Wicket

How to start the Wicket application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar server/target/wicketserver-1.0-wicket-snapshot.jar server wicket.yml`
1. To check that your application is running enter url `http://localhost:9000

Health Check
---

To see your applications health enter url `http://localhost:9000/admin/healthcheck?pretty=true`
