# Threader

### Main goal:
 Goal of the project is to provide a service able to store conversation threads
 where users may start a new conversation thread out of any word or phrase 
 of another one with clear linkage to the source.

### Sub-goals:
- Provide user interface for desktop and mobile platforms.
- Provide integration API.

### Requirements:
- Continuous delivery.
- Horizontal scalability.
- Full API and code documentation.
- Full test coverage including unit, functional and integration tests.

### Build:
- Required software: `Java 11`, `Maven 3.6.X`
- Build command: `mvn clean install -DskipTests`
- Test command: `mvn surefire:test`

##### Current stack:
- **Core**: Java 11, spring-boot, spring-web, spring-aop
- **Test**: JUnit5, spring-test, spring-cloud-contract, AssertJ
- **Documentation & Reporting**: Swagger3, log4J2