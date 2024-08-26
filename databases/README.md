# Understanding Databases
I decided to create a project that would help me understand the different types of databases and how to interact
with them using Java. I have very minimal experience in using an SQL database, springdata JPA and I understand the architecture
behind it, like the use of data models, repositories, and services. 

I know that just slapping a bunch of databases together might create a chaotic mess, but I think it would be a fun 
challenge to try and integrate them all into a single application.

This project will explore relational databases (RDBMS), NoSQL databases, and in-memory databases, demonstrate how to 
integrate them with a Java application using Spring Boot. I will also go over some main concepts of databases, such as 
ACID and BASE principles, normalization, CAP. If pertinent I will also cover more advanced topics like query optimization,
indexes, sharding, replication, and more. 

The initial part will go over the theories behind and the second part will attempt to implement a system that uses
MySQL, PostgreSQL, MongoDB, Cassandra, Redis and H2 to store different parts of the data. It will involve setting up the project with
Spring Boot, Spring Data, JPA, Hibernate, JDBC, and the necessary database drivers, and we will need to deploy each database
independently which will also be fun.

# Theoretical Overview
## ACID and BASE Principles
ACID (Atomicity, Consistency, Isolation, Durability):
- Atomicity: Ensures that each transaction is treated as a single "unit," which either completes entirely or not at all. 
- This means if one part of the transaction fails, the entire transaction fails and the database remains unchanged.
- Consistency: Guarantees that a transaction will bring the database from one valid state to another, maintaining all 
defined rules, such as constraints and triggers.
- Isolation: Ensures that transactions occur independently without interference. This means the operations of one 
transaction are not visible to others until the transaction is complete.
- Durability: Means that once a transaction is committed, it remains in the database even in the case of a system failure.

ACID properties are crucial for applications where data integrity and correctness are critical, such as banking systems 
and financial applications. They ensure that transactions are reliable and that data remains consistent.
An actual example of ACID is a bank transfer. If you transfer money from one account to another, the transaction must be
atomic, meaning it either completes successfully (the money is deducted from one account and added to the other) or fails
(no money is transferred). It must also be consistent, meaning the total amount of money in both accounts remains the same
before and after the transaction. The transaction must be isolated, so other transactions don't interfere with it, and it
must be durable, meaning the money transfer remains in the system even if there's a power outage or system crash.

BASE (Basically Available, Soft state, Eventual consistency):
- Basically Available: Means that the system guarantees the availability of data, although it may not always be up-to-date.
- Soft state: Indicates that the state of the system may change over time, even without new input, due to eventual consistency.
- Eventual consistency: Means that while the system may not be immediately consistent, it will eventually become consistent 
after some time.

BASE properties are more suited for applications requiring high availability and scalability, such as social media 
platforms or large-scale web applications. They prioritize responsiveness and flexibility over immediate consistency.
An example of BASE is a social media feed. When you post a new update, it may not be immediately visible to all users due
to eventual consistency. The system prioritizes availability, ensuring that users can still access the feed even if it's not
immediately up-to-date.

## CAP Theorem

The CAP Theorem (Consistency, Availability, Partition Tolerance) states that in a distributed system, it’s impossible to 
achieve all three of the following guarantees simultaneously:
- Consistency: Every read receives the most recent write.
- Availability: Every request receives a response, whether successful or not.
- Partition Tolerance: The system continues to operate despite network partitions or communication failures.

This theorem is crucial when designing distributed databases and systems, as it helps in making trade-offs based on the 
system’s requirements.

For example, in a distributed database, if a network partition occurs, the system must choose between maintaining
consistency (ensuring all nodes have the same data) or availability (ensuring all nodes can respond to requests). It’s
impossible to have both in the presence of partition tolerance, so the system must decide which guarantees to prioritize.

## Normalization and Denormalization

Normalization is the process of organizing data to reduce redundancy and improve data integrity. This involves breaking 
down large tables into smaller, related tables and defining relationships between them, often following rules like 1NF, 
2NF, and 3NF. This approach minimizes duplication and maintains consistency.

Denormalization, on the other hand, involves combining tables to reduce the number of joins and improve query performance. 
This is typically done in read-heavy scenarios where performance is prioritized over maintaining strict normalization. 
It’s useful for optimizing queries by reducing the complexity of joins. This obviously comes at the cost of redundancy and
even potential data inconsistency, as the data needs to be updated in multiple places.

## Indexes and Query Optimization

Indexes are data structures that enhance the speed of data retrieval operations. They work similarly to a book's index, 
allowing quick location of information. While indexes improve read performance, they can add overhead to write operations 
and increase storage requirements. Common types include primary keys (unique identifiers) and secondary indexes (indexes 
on non-primary key columns).

An example of an index is a book's index, which lists the page numbers where specific topics are discussed. This allows
readers to quickly find relevant information without scanning the entire book. In terms of a database, an index on a column
can speed up queries that filter or sort based on that column. It is slightly different from a primary key, which uniquely
identifies each row in a table. Often indexes are managed by the database itself, but they can also be created manually.

Query Optimization involves improving the performance of database queries by analyzing the query, data, and schema to 
select the most efficient execution plan. This process aims to reduce query execution time and resource consumption.
An example of query optimization is rewriting a query to use an index or adding a new index to improve performance. Query 
optimization may be performed proactively before the query is done on the database, or by the database itself when the query
is executed. Generally speaking, query optimization is a complex topic that requires a deep understanding of the database
system and the underlying data.

## Sharding and Partitioning

Sharding is the process of dividing a large database into smaller, more manageable parts called shards. Each shard is 
stored on a separate server, which helps in distributing the load and improving performance. Sharding is particularly 
useful in horizontally scaling databases to handle large volumes of data and traffic. 
An example of sharding is splitting a user database based on the first letter of the user's last name. Users with last
names starting with A-M are stored on one shard, while users with last names starting with N-Z are stored on another shard.
This allows the database to distribute the user data across multiple servers, reducing the load on any single server. 

Partitioning involves dividing a large table into smaller, more manageable parts called partitions. Each partition is 
stored on a separate disk to improve query performance and reduce I/O bottlenecks.

## Operations and Fault Tolerance
In terms of operations, databases need to be monitored, maintained, and optimized to ensure they perform efficiently and
reliably. This includes tasks like backup and recovery, security management, performance tuning, and capacity planning.
Fault tolerance refers to a system's ability to continue operating in the event of hardware or software failures. This
involves redundancy, failover mechanisms, and disaster recovery plans to ensure the system remains available and functional
even during failures. Fault tolerance is crucial for mission-critical applications where downtime can have severe consequences.
It's easy to imagine how awkward it would be if a bank's database went down in the middle of the day, or if a social media
platform lost all its user data. Fault tolerance ensures that even if something goes wrong, the system can recover quickly
and continue operating.

## Security and Compliance
Security is a critical aspect of database management, as databases often contain sensitive and confidential information.
This includes user data, financial records, intellectual property, and more. To ensure data security, systems often have 
multiple layers of protection, for example, authentication, authorization, encryption, and auditing. 

Some security measures are present at the system's API level, like authentication and authorization, while others are at the database
level, like encryption and access control. Security is a multi-faceted topic that requires a combination of technical
solutions, policies, and procedures to protect data from unauthorized access, modification, or disclosure.

Compliance refers to adhering to legal and regulatory requirements related to data management. This includes laws like GDPR,
HIPAA, PCI DSS, and more, which mandate how data should be stored, processed, and protected. Compliance ensures that
organizations handle data responsibly and ethically, protecting user privacy and maintaining trust.

# Databases Overview
Let's imagine we just moved into a new house and we need to organize our belongings. We can store them in different places
based on their type and usage. For example, we can keep our clothes in the closet, books on the shelf, and kitchen utensils
in the drawers. This is one way to sort our belongings, arguably the most normal. However, one take I wanted to make is that
we can also store our belongings in different places from those, and that would be absolutely fine, it really depends if we
are safe where they are, if they are accessible and if we will know where to find them when we need them. Technically speaking,
storing books in the kitchen oven offers a great deal of space and dust protection, and it may be the preferred place to
store a book if we like to read in the kitchen (that way we don't have to walk all the way to the living room to get a book).

I start with this example because it helped me think about the different types of databases and how they store data. The
important part is not how the data is stored, but how we can access it. Going over to digital side, if our business requires
us to obtain data quickly, we might choose a database that can provide fast access to data, like an in-memory database. If we
need to store large amounts of unstructured data, we might choose a NoSQL database. If we need to ensure data consistency and
integrity, we might choose a relational database. So each type has trade-offs and it's important to choose the right one for
the job. Obviously it is difficult to clearly understand their strenghts and weaknesses without actually using them, so we

The only worry I have is that, considering the small size of the project, we might not be able to see the full potential of
each database. But I will try to "push" each database so we can get some data that might indicate their different performance.
At the time of writing I am still unsure how to do that, but I will figure it out as I go.

# Relational Databases, NoSQL Databases, and In-Memory Databases
There are three main types of databases that we will explore in this project: relational databases (RDBMS), NoSQL databases,
and in-memory databases. There are some special types of databases like graph databases, time-series databases, and document
databases, but we will focus on these three for now.

To make it easier to distinguish between their characteristics, I asked ChatGPT to create a table which compares them
based on a few key factors. I also asked for a star rating to indicate how well each type performs in each (Read this
with a grain of salt, as the ratings are based on the model's understanding of the topic). Side note: we can use
HTML to add colors to the text.

| **Factor**                     | **RDBMS**                                                | **NoSQL**                                              | **In-Memory Database**                               |
|--------------------------------|----------------------------------------------------------|--------------------------------------------------------|------------------------------------------------------|
| **Data Model**                 | Structured, table-based schema with defined relationships. | Flexible schema; supports various models (key-value, document, etc.). | Often supports a hybrid model with data in memory and optional persistence. |
|                                | <span style="color:green;">Strong data integrity and ACID compliance.</span> | <span style="color:green;">High flexibility and horizontal scaling.</span> | <span style="color:green;">Extremely fast data access and real-time processing.</span> |
|                                | <span style="color:#FF6F6F;">Rigid schema, difficult to scale horizontally.</span> | <span style="color:#FF6F6F;">Limited ACID compliance, lacks standardized query language.</span> | <span style="color:#FF6F6F;">Limited by memory size, persistence can be challenging.</span> |
|                                | ★★★★☆                                                   | ★★★★☆                                                  | ★★★★☆                                                 |
| **Performance and Scalability**| Optimized for complex queries and transactions.          | Designed for high availability and easy horizontal scaling. | Extremely low latency, ideal for high-throughput scenarios. |
|                                | <span style="color:green;">Supports complex queries, indexes, and joins.</span> | <span style="color:green;">Handles large data volumes and traffic effectively.</span> | <span style="color:green;">Fast access to data due to in-memory storage.</span> |
|                                | <span style="color:#FF6F6F;">Struggles with large-scale horizontal scaling.</span> | <span style="color:#FF6F6F;">Performance may degrade without careful data modeling.</span> | <span style="color:#FF6F6F;">High memory costs, limited data size.</span> |
|                                | ★★★☆☆                                                   | ★★★★☆                                                  | ★★★★★                                                 |
| **Consistency, Availability, Durability** | Strong consistency with ACID properties.                 | Typically supports eventual consistency; CAP theorem applies. | Often strong consistency, with optional persistence to disk for durability. |
|                                | <span style="color:green;">Ensures data integrity and durability.</span> | <span style="color:green;">High availability and partition tolerance.</span> | <span style="color:green;">Data is instantly accessible, with optional durability.</span> |
|                                | <span style="color:#FF6F6F;">Availability and scalability can be limited.</span> | <span style="color:#FF6F6F;">Weaker consistency guarantees in some implementations.</span> | <span style="color:#FF6F6F;">Risk of data loss if not properly persisted.</span> |
|                                | ★★★★★                                                   | ★★★☆☆                                                  | ★★★★☆                                                 |
| **Use Cases**                  | Transactional systems, financial applications, complex queries. | Big data, distributed systems, applications needing high scalability. | Real-time analytics, caching, session management, and applications needing low latency. |
|                                | ★★★★☆                                                   | ★★★★★                                                  | ★★★★★                                                 |
| **Cost**                       | Varies, often higher for large-scale vertical scaling.   | Typically lower cost for scaling, but can vary based on architecture. | High memory costs, especially at large scales.       |
|                                | ★★★☆☆                                                   | ★★★★☆                                                  | ★★★☆☆                                                 |
| **Query Language**             | SQL (standardized, powerful).                            | Varies by database (some use SQL-like queries, others custom). | May use key-based lookups or SQL-like languages depending on implementation. |
|                                | ★★★★★                                                   | ★★★☆☆                                                  | ★★★★☆                                                 |

# Tools and frameworks for Java-Database Interaction
## Drivers
It is good to keep in mind that a database is in itself a complex system. It often is written in a different language and
to communicate with it we need to use a specific API. Some are a bit more complex to work than others requiring either a 
lot of boilerplate code or a deep understanding of the database system's specific features. To simplify the interaction 
process, there are several tools and frameworks available in the Java ecosystem that make it easier for developers to work 
with databases.

To communicate with a database we need a driver (which sits in the outer layer of an onion model, for
example). A driver is a piece of software component that bridges the language gap between our system and the database.
When dealing with RDBMS databases, all drivers must follow a standard named JDBC (Java Database Connectivity). JDBC is
basically a prescription of how a driver can be interacted with from a more abstract level (lets say database is deep,
driver sits in the middle, and a more abstract layer is on top). An example of a JDBC specification is the use of a
Connection object to establish a connection to the database, a Statement object to execute SQL queries, and a ResultSet
object to retrieve query results. Following this example, a driver for MySql or Oracle would have to implement these
interfaces to be considered a JDBC driver.

When talking about NoSQL databases, the interaction is a bit different. NoSQL databases don't use a standardized query
language like SQL, so the drivers for these databases are more specific to the database system. For example, MongoDB has
its own Java driver that allows developers to interact with MongoDB databases using the MongoDB query language. The same
applies to other NoSQL databases like Cassandra, Redis, and more.

Putting this into an architectural perspective, let's imagine a clean architecture where the database sits outside the
backend. The driver is the outermost layer of the backend, followed by the repository. The repository is the layer that
interacts with the driver and translates the data into a format that the rest of the backend can understand. When we talk
about JDBC, we can see that creating a repository for different SQL databases is easier because they all follow the same
standard (if we apply inversion of control, we can even create a generic repository that can be used for any SQL database,
by injecting the driver at runtime). However, when we talk about NoSQL databases, we need to create a repository for each 
database system because they have different query languages and APIs. 

## Repositories, ORMs, JPA and Data models
The next level of abstraction after the database driver is the repository. A repository is a design pattern that acts as 
a mediator between the database and the rest of the application. It provides a way to perform CRUD operations (Create, 
Read, Update, Delete) while abstracting the underlying data access logic. Essentially, the repository translates application 
objects into database data and vice versa, enabling a clean separation of concerns within your application architecture.

When dealing with relational databases (RDBMS), the repository pattern often involves mapping application objects to 
database tables. This is where Object-Relational Mapping (ORM) comes into play. ORM is a tool that automates the conversion 
of data between incompatible type systems—in this case, between the object-oriented model used in applications and the 
relational model used in databases.

ORM frameworks frequently utilize the Java Persistence API (JPA), a standard specification that defines how objects should 
be persisted and retrieved from a relational database. JPA provides a set of common interfaces and annotations that various 
ORM frameworks can implement, allowing for a consistent programming model regardless of the specific ORM used.

For example, if you create a repository for a MySQL database using an ORM framework that implements JPA (like Hibernate, 
EclipseLink, or OpenJPA), you can easily switch to another relational database, such as PostgreSQL, simply by changing 
the JDBC driver and connection string in your configuration. This portability is possible because JPA abstracts the SQL 
queries through JPQL (Java Persistence Query Language), which is independent of the specific database. The ORM framework 
takes care of translating JPQL into the appropriate SQL dialect for the target database.

Working with NoSQL Databases
When dealing with NoSQL databases, the repository pattern becomes more complex because NoSQL databases do not use a 
standardized query language like SQL. Instead, each NoSQL database has its own unique query language and data model, which 
means that the repository must be tailored to the specific features of the database it interacts with.

For instance, when working with MongoDB, the repository needs to generate queries using MongoDB's query language, which 
is based on JSON-like documents. This is quite different from interacting with a database like Cassandra, which uses its 
own query language (CQL). As a result, the repository code for MongoDB will be different from that for Cassandra or Redis,
as each requires an understanding of the respective database's features, data model, and query language.

This lack of standardization across NoSQL databases means that developers need to write custom repositories for each NoSQL 
database they use, adding complexity to the application. While ORM frameworks and JPA provide a high level of abstraction 
and portability for relational databases, working with NoSQL databases often requires a deeper understanding of the specific 
database technology being used.

Regardless of the type of database, and if it uses an ORM or not, there is an object that represents the data in the
application. This object is called a data model or entity. The data model defines the structure of the data and how it
relates to other data in the application. For example, if you have a User entity, it might have fields like id, name, email,
and password. The data model is used to map the application's objects to the database tables, allowing for seamless data
manipulation and retrieval. It is generally best practice to have a separate domain model and respective data model, so
that the domain model can be used for business logic and the data model can be used for database operations (Even if the
information they carry is the same). Moreover some data models might even have more fields than the domain model, or may
be written in a different way like in JSON format (which is the case with MongoDB).

## Spring Data
Spring Data is a powerful framework that simplifies data access in Java applications. It provides a consistent programming
model for working with different types of databases, including relational databases, NoSQL databases, and in-memory databases.
It basically creates the repository classes for us, so we don't have to write them ourselves. To set up a repository, we
just need to create an interface that extends a Spring Data repository interface and Spring Data will automatically generate
the necessary CRUD methods based on the entity type and primary key. For example if we have a User entity with a Long id
field, we can create a UserRepository interface that extends JpaRepository<User, Long> and Spring Data will provide methods
like save, findById, findAll, delete, etc. We do not actually need to implement these methods, Spring Data does it for us
(it uses reflection to analyze the entity and generate the necessary queries). 

Similarly, for other types of databases, Spring Data provides specific repository interfaces like MongoRepository for MongoDB,
CassandraRepository for Cassandra, and RedisRepository for Redis. So as long as we follow the Spring Data conventions and
implement the necessary interfaces, springboot will create the concrete repository classes for us at runtime.

We will experiment with Spring Data in this project to see how it simplifies data access and repository management across
different types of databases. It should make it easier to understand.

## Comparative Analysis of Relational Databases: MySQL, PostgreSQL and Oracle

The following is just for reference. It was curated by ChatGPT and it provides a comparative analysis of three popular
relational databases: MySQL, PostgreSQL, and Oracle. Take it with a grain of salt, as it is based on the model's understanding
of the topic.

| **Factor**                     | **MySQL**                                       | **PostgreSQL**                                  | **Oracle Database**                              |
|--------------------------------|-------------------------------------------------|-------------------------------------------------|-------------------------------------------------|
| **Performance**                | High read performance, good for web apps.       | Balanced read and write performance.            | High performance, optimized for enterprises.    |
|                                | ★★★☆☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Scalability**                | Scales with sharding, manual management needed. | Supports horizontal scaling, native replication. | Highly scalable with strong distributed support. |
|                                | ★★★☆☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Data Types & Extensibility**  | Basic data types, limited extensibility.        | Rich data types and highly extensible.          | Extensive data types and strong extensibility.  |
|                                | ★★☆☆☆                                          | ★★★★★                                          | ★★★★★                                          |
| **Transaction Support**        | ACID compliant but lacks advanced features.     | Full ACID compliance with strong concurrency.   | Advanced transaction management and compliance. |
|                                | ★★★☆☆                                          | ★★★★★                                          | ★★★★★                                          |
| **Compliance & Standards**     | Basic SQL standards with some limitations.      | Closely adheres to SQL standards with extra features. | Comprehensive SQL standards support with extensions. |
|                                | ★★☆☆☆                                          | ★★★★★                                          | ★★★★★                                          |
| **Licensing & Cost**           | Open-source with free and paid support.         | Open-source with commercial support available.  | Proprietary with high licensing costs.          |
|                                | ★★★★★                                          | ★★★★★                                          | ★★☆☆☆                                          |
| **Community & Support**        | Large community with extensive documentation.   | Strong community with active development.       | Enterprise support with a large user community. |
|                                | ★★★★☆                                          | ★★★★☆                                          | ★★★★☆                                          |
| **Ease of Use**                | Simple setup and management.                    | More complex but powerful tools.                | Complex setup with a steep learning curve.      |
|                                | ★★★★★                                          | ★★★★☆                                          | ★★★☆☆                                          |
| **Backup and Recovery**        | Tools like `mysqldump`, point-in-time recovery.  | Robust tools including `pg_dump` and archiving. | Advanced tools like RMAN and Flashback.         |
|                                | ★★★★☆                                          | ★★★★★                                          | ★★★★★                                          |
| **High Availability & Replication** | Master-slave and group replication.             | Streaming and logical replication.              | Comprehensive solutions including RAC and Data Guard. |
|                                | ★★★☆☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Performance Tuning**         | Query optimizer, indexing strategies.           | Query planner, indexing options.                | Advanced tuning features like AWR.              |
|                                | ★★★★☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Analytics & BI Support**     | Basic support, third-party tools often needed.  | Advanced capabilities with extensions.          | Extensive support with Oracle OLAP.             |
|                                | ★★★☆☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Integration & Ecosystem**    | Broad integration, strong tool ecosystem.       | Extensive extensions and strong integrations.   | Deep integration with Oracle products.          |
|                                | ★★★★☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Data Integrity & Concurrency** | Basic support with standard isolation levels.   | Advanced concurrency control and integrity.     | Strong control with detailed locking mechanisms. |
|                                | ★★★☆☆                                          | ★★★★★                                          | ★★★★★                                          |

## Comparative Analysis of NoSQL Databases: MongoDB, Cassandra, and Redis

The following is just for reference. It was curated by ChatGPT and it provides a comparative analysis of three popular
NoSQL databases: MongoDB, Cassandra, and Redis. Take it with a grain of salt, as it is based on the model's understanding
of the topic.

| **Factor**                   | **MongoDB**                                    | **Cassandra**                                  | **Redis**                                      |
|------------------------------|------------------------------------------------|-----------------------------------------------|------------------------------------------------|
| **Data Model**               | Document-oriented (JSON-like documents)       | Wide-column store (rows and columns)          | Key-value store (simple data structures)      |
|                              | ★★★★☆                                          | ★★★☆☆                                          | ★★★☆☆                                          |
| **Query Language**           | MongoDB Query Language (MQL)                   | Cassandra Query Language (CQL)                 | Redis commands (e.g., GET, SET)                |
|                              | ★★★☆☆                                          | ★★★☆☆                                          | ★★★☆☆                                          |
| **Consistency**              | Strong consistency with eventual consistency for distributed setups | Tunable consistency (eventual by default)     | Strong consistency with single-node setups, eventual in clusters |
|                              | ★★★★☆                                          | ★★★☆☆                                          | ★★★☆☆                                          |
| **Scalability**              | Horizontal scaling (sharding)                  | High scalability with automatic data partitioning | Limited by memory; horizontal scaling possible |
|                              | ★★★★☆                                          | ★★★★★                                          | ★★★☆☆                                          |
| **Use Cases**                | General-purpose, flexible schema (e.g., content management, real-time analytics) | Large-scale, distributed systems (e.g., time-series data, recommendation engines) | Fast in-memory caching and real-time analytics |
|                              | ★★★★☆                                          | ★★★★★                                          | ★★★★☆                                          |
| **Performance**              | Good for read and write operations with complex queries | Optimized for write-heavy workloads and high availability | Extremely fast for read and write operations due to in-memory nature |
|                              | ★★★★☆                                          | ★★★★☆                                          | ★★★★★                                          |
| **Data Persistence**         | Persistent storage with configurable replication | Persistent storage with high availability    | In-memory with optional persistence (RDB/AOF) |
|                              | ★★★★☆                                          | ★★★★☆                                          | ★★★★☆                                          |
| **Schema Flexibility**       | Flexible schema with dynamic document structures | Schema-less but requires predefined column families | No schema, key-value pairs can be of different types |
|                              | ★★★★☆                                          | ★★★★☆                                          | ★★★☆☆                                          |
| **Indexing**                 | Support for secondary indexes and full-text search | Limited indexing capabilities (e.g., primary key, secondary indexes) | Basic indexing via keys and sorted sets       |
|                              | ★★★★☆                                          | ★★★☆☆                                          | ★★★☆☆                                          |
| **Deployment & Management**  | Managed cloud services available (e.g., MongoDB Atlas) | Managed services available (e.g., DataStax)   | Managed cloud services available (e.g., Redis Enterprise) |
|                              | ★★★★☆                                          | ★★★★☆                                          | ★★★★☆                                          |

# Setting up the project
1. Let's start by creating a new project on Spring Initializr: https://start.spring.io/
2. Dependency list: 
    - Spring Data JPA 
    - Spring Data MongoDB 
    - Spring Data Cassandra Spring Data Redis 
    - Spring Boot Starter JDBC 
    - MySQL Driver 
    - PostgreSQL Driver 
    - H2 Database (for in-memory database)
    - Hibernate (should be included with Spring Data JPA)

# Setting up all the databases using Docker
To make our lives easier, we will use Docker to set up all the databases we need for this project. Docker allows us to
launch containers with pre-configured databases, making it easy to deploy and manage them. So we can create all of them 
at the same time, we will use Docker Compose, which is a tool for defining and running multi-container Docker applications. 
Feel free to pick create a file named `docker-compose.yml` to the root and add the following configuration:
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:latest
    container_name: mysql-db
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: mydb
    ports:
      - "3306:3306"
    networks:
      - my-network

  postgres:
    image: postgres:latest
    container_name: postgres-db
    environment:
      POSTGRES_PASSWORD: password
      POSTGRES_DB: mydb
    ports:
      - "5432:5432"
    networks:
      - my-network

  mongo:
    image: mongo:latest
    container_name: mongo-db
    ports:
      - "27017:27017"
    networks:
      - my-network

  cassandra:
    image: cassandra:latest
    container_name: cassandra-db
    ports:
      - "9042:9042"
    networks:
      - my-network

  redis:
    image: redis:latest
    container_name: redis-db
    ports:
      - "6379:6379"
    networks:
      - my-network

networks:
  my-network:
    driver: bridge
```
Understanding the code:
1. We need to specify where the images are coming from. In this case, we are using the latest versions of everything, and
they will be automatically downloaded from Docker Hub.
2. We are defining the services we want to create: MySQL, PostgreSQL, MongoDB, Cassandra, and Redis. H2 is not required.
3. For each service, we are specifying the image to use, the container name, and the ports to expose.
4. Regarding MySQL and PostgreSQL, we are setting the root password and the database name to create. Those are compulsory
fields when creating a new database. Without setting them the databases won't start.
5. We are creating a custom network called `my-network` to allow the containers to communicate with each other.
6. To run the containers, open docker desktop then navigate to the directory where the `docker-compose.yml` file is 
located and run the following:
```bash
docker-compose up
```
This will start all the containers and set up the databases. You can check if the containers are running by using the
following command:
```bash
docker ps
```
You should see containers named `mysql-db`, `postgres-db`, `mongo-db`, `cassandra-db`, and `redis-db` running. If you
encounter any issues, make sure Docker is installed and running on your system. You can also check on docker desktop if
the containers are running.

# Designing the Project Structure



Revised Project Plan Outline
Overview of Different Types of Databases:

Relational Databases (RDBMS):
Examples: MySQL, PostgreSQL
Key Features: Structured data, ACID compliance, use of SQL
NoSQL Databases:
Example: MongoDB
Key Features: Schema-less, flexible data models, horizontal scaling, BASE principles
In-Memory Databases:
Example: Redis
Key Features: Fast data access, key-value storage, used for caching
Components for Java-Database Interaction:

Spring Data: Simplifies data access in Java applications
ORM (Object-Relational Mapping): Maps Java objects to database tables
JPA (Java Persistence API): Standard for ORM in Java
Hibernate: Popular JPA implementation with additional features
JDBC (Java Database Connectivity): Low-level API for executing SQL queries
Database: The actual system storing the data (e.g., MySQL, PostgreSQL, MongoDB, Redis)
Key Database Concepts:

ACID (Atomicity, Consistency, Isolation, Durability)
BASE (Basically Available, Soft state, Eventual consistency)
CAP Theorem (Consistency, Availability, Partition tolerance)
Normalization and Denormalization
Indexes and Query Optimization
Sharding and Partitioning
Replication and Fault Tolerance
Practical Tutorial:

Objective: Create a system with different parts of data stored in different types of databases.
Goals:
Demonstrate integration between Java code and various databases.
Work with RDBMS, NoSQL, and in-memory databases in a single application.
Dbs to be used: MySQL, PostgreSQL, MongoDB, Cassandra, Redis, H2.
Tools and Frameworks: Spring Boot, Spring Data, JPA, Hibernate, JDBC, database drivers.

Dependencies to Include in Spring Initializr:
Spring Boot Starter Dependencies:

Spring Web: To build web applications, including RESTful services.
Spring Data JPA: For using JPA with Hibernate as the default ORM implementation.
Spring Data MongoDB: For interacting with MongoDB.
Spring Data Redis: For interacting with Redis.
Spring Boot DevTools: For development-time features like automatic restarts and live reload.
Database Drivers:

MySQL Driver: For connecting to MySQL databases.
PostgreSQL Driver: For connecting to PostgreSQL databases.
H2 Database (Optional): If you want an in-memory database for testing or simple development.
Other Essential Dependencies:

Lombok (Optional but Recommended): To reduce boilerplate code like getters, setters, etc.
Spring Boot Actuator (Optional): For monitoring and managing your application.
Steps to Set Up Your Project on Spring Initializr:
Go to Spring Initializr.

Project Metadata:

Project: Maven Project
Language: Java
Spring Boot Version: Choose the latest stable version
Group: com.example
Artifact: database-project
Name: Database Project
Description: A project integrating multiple databases with Spring Boot
Packaging: Jar
Java Version: 17 (or the version you prefer)
Add Dependencies:

Spring Web
Spring Data JPA
Spring Data MongoDB
Spring Data Redis
MySQL Driver
PostgreSQL Driver
H2 Database (optional)
Lombok (optional)
Spring Boot Actuator (optional)
Generate the Project and download the zip file.

Unzip and Import the project into your IDE (e.g., IntelliJ IDEA, Eclipse).