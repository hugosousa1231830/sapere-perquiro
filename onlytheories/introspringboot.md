## What is Spring Boot?
We will talk about springboot in its close-knit relationship with the spring framework. So I'll try to explain both together.

A framework is a chunk of code, written on top of a language's core library that solves common problems. A common example is
the ability of an application to connect to a database or the ability to serve web pages. We could set up everything from scratch,
but it's done so often that we have frameworks that automate most of the process.

We can see it as my_app -> framework -> language core library

JAVA has a framework called SPRING that offers tons of features, and springboot could be considered a sub-framework of 
spring: my_app -> Springboot -> Spring -> JAVA core library.

The spring framework is extremely powerful and highly configurable, but it is also complex and has a steep learning curve,
therefore springboot was created to simplify the process of setting up a spring application, but automating most of the
configuration and providing a set of tools to make the development process easier.

## Example of a problem being solved by the Spring framework
Imagine we want to connect our application to a database. A normal procedure, would be to plug in Hibernate (framework for
mapping an object-oriented domain model to a relational database), JPA (Java Persistence API, a specification for managing
persistence in Java applications), and a database driver (like MySQL). On top of that we would use Spring and Spring Data JPA
to manage the connection. And finally Springboot to automate the configuration process. For the sake of clarity on the importance
of these frameworks, I asked my buddy mentor ChatGPT to provide a simple example of how we would connect to a database without
using these frameworks. I have no idea if this is right, but the point is to show a big list full of shenanigans:
1. Add JDBC Driver to Your Project: 
2. Download the MySQL JDBC driver (mysql-connector-java.jar) and add it to your project’s classpath. 
3. Set Up Database Connection Properties: Define the database connection properties like URL, username, password, and 
driver class. 
4. Load JDBC Driver Class: Explicitly load the JDBC driver class using Class.forName(driverClass). 
5. Establish a Database Connection: Use the DriverManager class to create a connection to the database with the specified 
URL, username, and password. 
6. Create SQL Statements: Create SQL Statement or PreparedStatement objects to execute queries or updates. 
7. Execute SQL Queries and Process Results: Execute the SQL query and process the results returned by the database using 
ResultSet. 
8. Handle Transactions Manually: Manage database transactions using Connection.setAutoCommit(false) to begin, commit() to 
commit, and rollback() to rollback transactions in case of errors. 
9. Manage Entity Relationships: Manually manage relationships between entities, including fetching related data and maintaining 
referential integrity. 
10. Manually Map Data to Objects: Map the ResultSet data to Java objects manually, typically using getters and setters. 
11. Close Resources: Manually close ResultSet, PreparedStatement, and Connection objects to avoid resource leaks. 
12. Error Handling: Implement error handling using try-catch blocks to manage SQLExceptions and other potential issues. 
13. Manual Configuration for Connection Pooling (Optional): Manually configure and manage a connection pool for better 
performance using a library like Apache DBCP or HikariCP. 
14. Environment-Specific Configurations: Manually manage different configurations for different environments (development, 
testing, production) by using separate configuration files or code paths.

Well ain't nobody got time for dat. 

With frameworks, this process is simplified to, LITERALLY, a few lines of code. 

Obviously, one should have an idea of what is happening especially with stuff like springboot that automates most of the 
process. Not understanding this could lead to a lot of problems in production, and debugging would be a nightmare. I have
had tons of problems with springboot automating stuff on my personal projects, which took me hours to decipher. Which is 
why I decided to try and really sink my teeth into it. Nonetheless, the Spring framework is absolutely amazing on a technical 
standpoint, and I find it very interesting to learn about it.

Moving on.

## Inversion of control and Dependency Injection / Autowiring
The main concept to learn regarding Spring, is the way it handles dependencies. Spring uses a concept called Inversion of Control
(IoC), which is a design principle that allows us to remove the responsibility of creating objects from our code. For example,
our Controller class could very well instantiate an implementation of a Service class within its methods with new Service(), 
but this would make our code tightly coupled. By utilizing inversion of control, the Controller only needs to know it needs 
a Service (an interface, and not an actual implementation), and that the implementation gets injected (passed as an argument) 
during its construction (via constructor). Of course, we still need something to observe that the controller requires an 
implementation of a service, that knows how to create and knows to inject it. This is where the Spring framework comes in. 
Spring is the one that observes the dependencies, knows how to create them, and injects them where required.

Starting on how to program these sorts of features, we will need to understand the Spring concept: autowiring. The term 
autowiring could be seen as a synonym for injection. For example, when we inject a service in our controller, we are 
basically autowiring it. There are two ways to autowire a dependency in Spring, either we pop the @Autowired annotation
on top of the parameter:
```java 
@RestController
public class MyController {

   @Autowired
   private MyService myService;

   public MyController() {
   }
}
```
Although the above code is correct, it is not recommended to use the @Autowired annotation on parameters. This is because
most of the time, these parameters should be final, and @Autowired does not work with final parameters. Take a minute to 
notice that, because we are explicitly autowiring the service, it is not passed on the constructor, and we are  
forced to create a default constructor. This is not the recommended way to autowire a dependency in Spring.

The recommended way to autowire a dependency is by "letting spring autowire the dependencies through the constructor". 
One way to do it is by using the @Autowired annotation on the constructor, but it is not absolutely necessary, as Spring 
will automatically inject the dependencies:
```java
@RestController
public class MyController {

   private final MyService myService;
   
   // @Autowired - OPTIONAL - NOT REQUIRED
   public MyController(MyService myService) {
       this.myService = myService;
   }
}
```
The above code is the recommended way to autowire a dependency in Spring. It is also the most common way to do it. 

## Beans and Component Scanning
We talked about the fact that Spring can inject the required dependencies in our classes (by injecting dependencies I mean 
that Spring can create an instance of a particular class, like if it was doing new Class(), and pass it to the constructor 
of another class).

Introducing another term: Beans. A bean is the instance of a particular class that Spring creates. For example, if we have 
a class called MyService, and we want to inject it in our controller, we would say that when Spring creates the instance of 
MyService, it is creating a bean. We can think of Spring being a gardener who uses beans (the dependencies) in order to 
grow beanstalks (the plants that need the beans to grow).

Before diving into beans, it's important to understand the IoC container and application context. The IoC container is a 
conceptual framework within Spring. It represents the core mechanism for managing the lifecycle and dependencies of application 
components, known as beans. When Spring scans your application for classes and their dependencies, it creates instances of 
these classes and manages them through the IoC container. The ApplicationContext is a concrete implementation of this concept 
and serves as the actual container where all the beans are stored (the bean jar). When we want to use a bean, we ask the 
application context to give it to us. This is a very high-level overview (there are classes like BeanFactory, specific 
contexts like WebApplicationContext, AnnotationConfigApplicationContext, etc.), but this is confusing enough as it is.

Moving on, Spring needs to know which classes it has to manage and create beans for. There are several information sources 
Spring can use to know this. We'll start with Spring core functionality, then expand to Spring Boot features:

1. Component Scanning:
Spring goes through the classes in the specified packages and looks for annotations (@Component, @Service, @Repository, 
@Controller). When it finds one, it creates a bean for it (FYI the bean's name is the class name with the first letter
lowercased, like myService). By default, Spring scans the package of the main application class and its sub-packages. This 
can be customized using @ComponentScan(basePackages = "com.example"), generally used in a configuration class.
Note: The @Component annotation is the most generic one, and the other three are specialized versions of it. They all serve 
the same purpose, but they provide additional context and clarity for developers. For example, the @Service annotation 
indicates that the class is a service component, typically containing business logic. But in terms of Spring functionality, 
they are all the same.

2. Configuration Classes:
Aside from component scanning, we can directly define beans in configuration classes. Configuration classes can be created 
by annotating a class with @Configuration. Within these classes, methods annotated with @Bean return instances that are 
registered as beans in the Spring context. This is a way to explicitly define beans, for example:
```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        MyService newService = new MyService();
        return newService;
    }
}
```
The above example would be equivalent to the following:
```java
@Service
public class MyService {
}
```
Creating custom configuration classes is useful when we need to define complex beans, or we want a bit more control over 
the bean creation process.

3. XML Configuration:
This is a legacy way of defining beans in Spring applications. We can define beans in an XML file and load it into the
application context. This is not recommended for new projects, as it is less flexible and harder to maintain compared to
Java-based configuration.

Moving on to Springboot:
1. Autoconfiguration:
Spring Boot provides an additional layer of functionality called autoconfiguration (@AutoConfiguration). This feature
basically allows Spring Boot to automatically create a bunch of beans based on the dependencies in your project (for example, 
on Maven projects it scans the POM). For example, if we put JPA-related dependencies in our project, Spring Boot will
automatically create a DataSource bean, an EntityManagerFactory bean, and a TransactionManager bean, for example. If we 
were simply using Spring, we would have to create these beans ourselves using a configuration class. Spring Boot does this 
by scanning the classpath for certain classes and dependencies, and based on what it finds, it creates the beans. This is 
a very powerful feature, but it can also be a bit confusing, as it can create beans that we don't want or need. 

2. SpringBootApplication:
As mentioned before. When using springboot in our main class we can use the @SpringBootApplication annotation, which combines 
@ComponentScan, @EnableAutoConfiguration, and @Configuration. This annotation tells Spring Boot to scan the classpath for 
components and apply autoconfiguration based on what it finds.

3. Controlling autoconfiguration:
If we want to control the autoconfiguration process, we can use annotations like @ConditionalOnClass, @ConditionalOnMissingBean, 
and @ConditionalOnProperty . These annotations allow us to specify conditions that must be met for a bean to be created. 
For example, we can use @ConditionalOnClass to specify that a bean should only be created if a certain class is present in 
the classpath.

4. Application.properties:
Application.properties is specific to Spring Boot (not Spring). It is a file where we can define properties that Spring Boot
will use to configure the application. For example, we can block the autoconfiguration of a bean by setting a property in
application.properties. This is a very powerful feature, as it allows us to configure the application without changing the
code. I'll give this a dedicated section later on.

## Spring Boot Starters and Spring Initializr
Spring Initializr is a web-based tool that allows us to create a new Spring Boot project. And for me, it is the best way to
start a new dummy project. https://start.spring.io/

Spring initializr is a convenient way of quickly creating a project with the following features:
- Project metadata: Group, Artifact, Name, Description, Package name, Java version.
- Build system: Maven or Gradle.
- Language: Java, Kotlin, Groovy.
- Spring Boot version.
- Dependencies: A list of dependencies that we can add to our project. 

Regarding dependencies, there is a load of them, I will mention a few:
- Lombok - Also known locally as the best thing ever - Lets us create classes with barely any code.
- Spring Web - For creating web applications. Comes with a TomCat server.
- Spring Data - For working with databases. This is a very powerful tool that allows us to work with databases without
writing a single line of SQL. It also works with non-relational databases like MongoDB, Cassandra, Redis, etc.
- Spring for Apache Kafka - For working with Kafka.

Anyway, there is loads, and if all of them are as useful as the ones I mentioned (which I have no doubt they are), then
it just illustrates how powerful Spring Boot is.

## Application Properties
Application.properties (or application.yml) is a file where we can define properties that Spring Boot will use to configure 
the application. For example, we can define the port that the application should run on by setting the server.port property 
in application.properties (remember springboot can come with a TomCat server!), or set up a database connection by setting 
the spring.datasource.url, spring.datasource.username, and spring.datasource.password properties. We can also use 
application.properties to configure logging, security, and other aspects of the application.

The properties in application.properties can be overridden by setting environment variables or by passing command-line
arguments when starting the application. This is useful for configuring the application in different environments (development,
testing, production) without changing the code. For example, if we have:
```properties
server.port=8080
```
We can override this by setting the server.port property as an environment variable via the command line:
```shell
java -jar myapp.jar --server.port=9090
```

## Testing in Spring Boot
Testing in springboot is done using JUnit. We can test our controllers, services, repositories, etc. by writing test classes
that use JUnit assertions to verify that the code behaves as expected. Spring Boot provides a number of annotations that
make it easy to write tests. For example, we can use the @SpringBootTest annotation to load the application context and
inject beans into our test classes. We can also use the @MockBean annotation to mock dependencies and @WebMvcTest to test
controllers.

I will not go over more of this for now. I think springboot testing needs to be properly explained and practiced using live
code. And I am not there yet. I will come back to this later!
