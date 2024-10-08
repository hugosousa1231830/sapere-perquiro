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
In this section, we'll explore how to implement repositories using Java Spring Boot and Spring Data across various databases, 
including MySQL, PostgreSQL, MongoDB, Cassandra, and Redis. By focusing on practical use cases, we'll highlight the unique 
characteristics of each database, starting with the basic setup and configuration required to integrate these technologies 
into a Spring Boot application.

We'll begin by implementing basic CRUD (Create, Read, Update, Delete) operations, demonstrating how Spring Data repositories 
can simplify data management across different database paradigms, from traditional relational models to document-oriented 
and key-value stores. Following this, we'll delve into custom querying, where we'll explore how each database handles more 
complex data retrieval scenarios, showcasing both the strengths and limitations inherent to each system.

Additionally, we'll discuss transaction management and data consistency, emphasizing the differences in how these databases 
ensure data integrity.

## The dataset
To demonstrate the capabilities of each database, we'll use a simple dataset consisting of a content management system with
authors, books, and publishers:

| **Entity** | **Field**     | **Description**                                                        |
|------------|---------------|------------------------------------------------------------------------|
| **Author** | `id`          | Unique identifier for the author                                       |
|            | `name`        | Full name of the author                                                |
|            | `address`     | Author address                                                         |
| **Publisher** | `id`          | Unique identifier for the publisher                                    |
|            | `name`        | Name of the publisher (must be unique)                                 |
|            | `address`     | Physical or mailing address of the publisher                           |
| **Book**   | `id`          | Unique identifier for the book                                         |
|            | `title`       | Title of the book                                                      |
|            | `genre`       | Genre or category of the book                                          |
|            | `authorId`    | Foreign key/reference to the `id` in the `authors` entity       |
|            | `publisherId` | Foreign key/reference to the `id` in the `publishers` entity |

## The architecture
We will be using clean architecture to organize our project. This architectural pattern emphasizes separation of concerns
and modularity, making it easier to maintain and extend the application. It also works great in one of our objectives which
is to make the application work with different databases, therefore inversion of control is a must. The layers will 
include the following components:
- Domain Model: Represents the data entities used in the application (Authors, Books, Publishers).
- Controller: Handles incoming HTTP requests, processes them, and returns the appropriate HTTP response. There will be a
single controller for each entity (Authors, Books, Publishers), and they should work for all databases.
- Service: Contains the business logic and acts as an intermediary between the controller and the repository. There will
be a single service for each entity, and they should work for all databases.
- Repository: Communicates with the database to perform CRUD operations and data retrieval. For SQL databases, we will use
a single JPA repository for each entity. For NoSQL databases, we will create custom repositories tailored to each database.
- Data Model: Represents the data entities used in the application. Each entity will have a corresponding model class that
defines the structure of the data. Even though it should match the domain model, we probably will create a separate class 
for each database to avoid confusion.

## The use cases
We will be implementing a set of use cases to demonstrate the capabilities of each database. These use cases will cover basic
CRUD operations, complex queries, transaction consistency, and more. By comparing how each database handles these scenarios,
we can gain insights into their strengths and limitations. The use cases will be designed to showcase the unique features of
each database, highlighting their suitability for different types of applications. Check the table below for a detailed
description of each use case:

| **Topic**                | **Database**                          | **Objective**                                                        | **Simple Use Case**                                               | **Complex Use Case**                                                                            |
|--------------------------|---------------------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| **CRUD Operations**      | **Relational Databases (MySQL, PostgreSQL, H2)** | Demonstrate basic and advanced CRUD operations.                      | Create a new author.                                             | Add a new book with associated author and publisher information.                              |
|                          | **MongoDB**                           | Demonstrate CRUD operations with a flexible schema.                   | Create a new document for an author.                              | Add a new document for a book with embedded author and publisher information.                 |
|                          | **Cassandra**                         | Show CRUD operations with a focus on high-throughput and scalability. | Create a new entry for an author.                                 | Add a new book entry with associated author and publisher information.                         |
|                          | **Redis**                            | Showcase CRUD operations using Redis data structures.                 | Add a new author using Redis hashes.                              | Add a new book with associated author and publisher details using Redis hashes and sets.      |
| **Complex Queries**      | **Relational Databases (MySQL, PostgreSQL, H2)** | Showcase the ability to perform and optimize complex queries.          | Find all books by a specific author using SQL queries.             | Use SQL joins and aggregations to find the number of books per genre and sort the results by count. |
|                          | **MongoDB**                           | Showcase the ability to perform and optimize complex queries.          | Find all books by a specific author using MongoDB’s query language. | Use MongoDB’s aggregation framework to find the number of books per genre and sort by count.  |
|                          | **Cassandra**                         | Demonstrate high-throughput querying and data modeling.               | Retrieve books by a specific tag.                                 | Retrieve books published in the last year, grouped by category, optimized for high read throughput. |
|                          | **Redis**                            | Highlight querying and caching capabilities.                           | Cache details of a popular book to improve read performance.      | Implement real-time updates for book information and maintain a sorted list of top books.     |
| **Transaction Consistency** | **Relational Databases (MySQL, PostgreSQL, H2)** | Illustrate transaction management and ACID properties.                | Update the title of a book and ensure consistency with ACID.      | Perform a multi-step operation where a book’s genre is updated and related statistics are adjusted. |
|                          | **MongoDB**                           | Show multi-document transaction support and consistency maintenance.  | Update a book’s publication date within a single document.        | Use multi-document transactions to update a book’s details and its associated author’s bio.  |
|                          | **Cassandra**                         | Explain eventual consistency and distributed transaction handling.    | Update the genre of a book and ensure consistency across nodes.   | Perform a distributed transaction to update book details and related metadata, managing eventual consistency. |
|                          | **Redis**                            | Explore transaction support and atomicity.                            | Update cached data for a book’s details using Redis transactions. | Use Redis transactions (MULTI/EXEC) to update book details, ensuring atomicity and consistency. |

Also find below a detailed description of each use case, per database:

| **Database**             | **Use Case**                                              | **Details**                                                                                                                                               |
|--------------------------|-----------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Relational Databases** | **Load Sample Data**                                     | Load a significant sample size of data into the `authors`, `books`, and `publishers` tables.                                                              |
|                          | **Retrieve Details of a Specific Author by ID**         | Query the `authors` table by `author_id` to retrieve details.                                                                                             |
|                          | **Update the Address of an Existing Author**       | Update the `address` field in the `authors` table for the specified `author_id`.                                                                          |
|                          | **Delete a Specific Publisher**                         | Delete a row from the `publishers` table where `publisher_id` matches the specified ID.                                                                   |
|                          | **Add a New Book with Associated Author and Publisher Information** | Insert a new row into the `books` table, linking it with existing `author_id` and `publisher_id` values.                                                  |
|                          | **Retrieve All Books Written by a Specific Author**     | Perform a join query between `books` and `authors` tables to list all books for the specified author.                                                     |
|                          | **Update the Title and Genre of a Book**                | Update the `title` and `genre` fields in the `books` table for the specified `book_id`, ensuring related entities (like author) are updated.              |
|                          | **Delete an Author and Handle Associated Books**         | Delete a row from the `authors` table and ensure that all related books in the `books` table are handled.                                                 |
| **MongoDB**             | **Load Sample Data**                                     | Insert a significant amount of documents into the `authors`, `books`, and `publishers` collections.                                                       |
|                          | **Retrieve Details of a Specific Author by ID**         | Query the `authors` collection by `author_id` to retrieve the document.                                                                                   |
|                          | **Update the Email Address of an Existing Author**      | Update the `email` field in the document for the specified `author_id`.                                                                                   |
|                          | **Delete a Specific Publisher**                         | Delete a document from the `publishers` collection where `publisher_id` matches the specified ID.                                                         |
|                          | **Add a New Book with Embedded Author and Publisher Information** | Insert a new document into the `books` collection with embedded fields for author and publisher details.                                                  |
|                          | **Retrieve All Books by a Specific Author**             | Query the `books` collection by author’s ID or name, assuming author details are embedded or referenced.                                                  |
|                          | **Update the Title and Genre of a Book**                | Update the `title` and `genre` fields in the document of the `books` collection.                                                                          |
|                          | **Delete an Author and Handle Related Books**           | Delete a document from the `authors` collection and handle related book documents, potentially using application logic to update or delete related books. |
| **Cassandra**           | **Load Sample Data**                                     | Insert a significant amount of rows into the `authors`, `books`, and `publishers` tables, optimized for high-throughput.                                  |
|                          | **Retrieve Details of a Specific Author by ID**         | Query the `authors` table by `author_id` using Cassandra’s optimized read queries.                                                                        |
|                          | **Update the Email Address of an Existing Author**      | Update the `email` field in the `authors` table for the specified `author_id`, considering Cassandra’s eventual consistency.                              |
|                          | **Delete a Specific Publisher**                         | Delete a row from the `publishers` table where `publisher_id` matches the specified ID.                                                                   |
|                          | **Add a New Book with Associated Author and Publisher Information** | Insert a new row into the `books` table, linking it with existing `author_id` and `publisher_id` values.                                                  |
|                          | **Retrieve All Books Published in the Last Year**       | Query the `books` table for books published in the last year, grouped by category, optimized for high read throughput.                                    |
|                          | **Update the Title and Genre of a Book**                | Update the `title` and `genre` fields in the `books` table for the specified `book_id`, considering Cassandra’s data model.                               |
|                          | **Delete an Author and Handle Associated Books**         | Delete a row from the `authors` table and ensure that all related books in the `books` table are handled.                                                 |
| **Redis**               | **Load Sample Data**                                     | Use Redis data structures to store a significant amount of data for authors, books, and publishers.                                                       |
|                          | **Retrieve Details of a Specific Author by ID**         | Query Redis for author details using hashes or other data structures.                                                                                     |
|                          | **Update the Email Address of an Existing Author**      | Update the `email` field for an author in Redis, using hashes or similar data structures.                                                                 |
|                          | **Delete a Specific Publisher**                         | Remove data for a publisher from Redis.                                                                                                                   |
|                          | **Add a New Book with Associated Author and Publisher Information** | Store a new book in Redis, linking it with existing author and publisher data.                                                                            |
|                          | **Retrieve All Books by a Specific Author**             | Query Redis for books associated with a specific author.                                                                                                  |
|                          | **Update the Title and Genre of a Book**                | Update the `title` and `genre` fields in Redis for a specific book.                                                                                       |
|                          | **Delete an Author and Handle Associated Books**         | Remove an author and manage related books from Redis.                                                                                                     |

# Coding the infrastructure 
## Domain layer - Enterprise business rules
We will start by creating our entities: Authors, Books, and Publishers. I am not particularly worried about the atomicity 
of the object itself, in fact I'll be creating them using Lombok, which sucks for atomicity. Nevertheless let us assume
all object fields are atomic and required and if we need to change any non-PK field, we can simply edit the object.

IMPORTANT NOTE: This is the author coming in from the future, later on I will try to use these domain objects as data models
by slapping all kinds of annotations together. This worked for a while, but as I was adding more databases, lots of conflicts
started to appear, for example springboot asking for beans just by analyzing annotations. This to say that later on, 
during my approach to Redis, I will undo annotating these domain objects, and will create separate data models for each
database. This is a good practice, as it allows for a better separation of concerns and avoids conflicts. And I learned 
that the hard way. TLDR: If you are following this, when I talk about annotating domain objects, just jump over to check
how I did the data models and the mappers. Unless you want to suffer what I did, which is also a learning experience.
To clarify, we maintain the lombok annotations, but when we start annotating for JPA and so on (like @Entity, @Table, etc),
just ignore me and go for the data models instead.

Under the root package, create a domain package and slap the following classes in there:
```java
package tutorials.databases.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    private String id;
    private String name;
    private String address;
}
```
```java
package tutorials.databases.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {
    private String id;
    private String name;
    private String address;
}
```
```java
package tutorials.databases.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private String id;
    private String title;
    private String genre;
    private String authorId;
    private String publisherId;
} 
```
As mentioned before, these classes are using lombok to generate getters, setters, constructors, and other boilerplate code.
Which means I can't use the final keyword to make the fields atomic, but because we will be inserting the data, and we have
no one breaking our code, we can assume the safety and integrity of the data. As long we are aware of this, we can move on.

# Application layer - Application business rules layer
In this layer, we will develop services that interact with repositories. Since we have three entities, we will need to 
create three corresponding services.

At this point, we don’t have any repositories set up yet, but our plan is to have one repository for each entity in the 
database. The services, however, should be designed independently of the specific repository implementations. To achieve 
this, we can use the Interface Segregation Principle to define service interfaces that will later be implemented by the 
repositories. This approach allows us to create and work with the services without needing to worry about the details of 
the repositories just yet.

For now, let’s create three service interfaces: AuthorRepository, PublisherRepository, and BookRepository. These interfaces 
will serve as the highest level of abstraction and will be implemented by the specific repository classes later on. We’ll 
revisit the implementation details of these repositories in a subsequent phase. For now, we can focus on developing the 
services and injecting these interfaces where needed:
```java
package tutorials.databases.service.repositories;

public interface AuthorRepository {
}
```
```java
package tutorials.databases.service.repositories;

public interface PublisherRepository{
}
```
```java
package tutorials.databases.service.repositories;

public interface BookRepository{
}
```

We will leave it like this for now. Now on for the services:

```java
package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;

   public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
      this.authorRepository = authorRepository;
      this.bookRepository = bookRepository;
   }

   public Author addAuthor(String name, String address) {
      Author author = Author.builder()
              .id(UUID.randomUUID().toString())
              .name(name)
              .address(address)
              .build();
      return authorRepository.save(author);
   }

   public Optional<Author> findById(String authorId) {
      return authorRepository.findById(authorId);
   }

   @Transactional
   public void deleteAuthor(String id) {
      bookRepository.deleteAllByAuthorId(id);
      authorRepository.deleteById(id);
   }
}

```

```java
package tutorials.databases.service;

import org.springframework.stereotype.Service;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {
   private final PublisherRepository publisherRepository;

   public PublisherService(PublisherRepository publisherRepository) {
      this.publisherRepository = publisherRepository;
   }

   public Publisher addPublisher(String name, String address) {
      Publisher publisher = Publisher.builder()
              .id(UUID.randomUUID().toString())
              .name(name)
              .address(address)
              .build();
      return publisherRepository.save(publisher);
   }

   public Optional<Publisher> findById(String id) {
      return publisherRepository.findById(id);
   }

   public void deletePublisher(String id) {
      publisherRepository.deleteById(id);
   }
}

```

```java
package tutorials.databases.service;

import org.springframework.stereotype.Service;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
   private final BookRepository bookRepository;

   public BookService(BookRepository bookRepository) {
      this.bookRepository = bookRepository;
   }

   public Book addBook(String title, String authorId) {
      Book book = Book.builder()
              .id(UUID.randomUUID().toString())
              .title(title)
              .authorId(authorId)
              .build();
      return bookRepository.save(book);
   }

   public Optional<Book> findById(String id) {
      return bookRepository.findById(id);
   }

   public void deleteById(String id) {
      bookRepository.deleteById(id);
   }

   public Collection<Book> findBooksByAuthorId(String authorId) {
      return bookRepository.findBooksByAuthorId(authorId);  // Assuming custom query method in BookRepository
   }


   public boolean deleteByAuthorId(String authorId) {
      return bookRepository.deleteAllByAuthorId(authorId);  // Assuming custom query method in BookRepository
   }
}
```

Certainly! Here’s a revised version of your text, with improved clarity and flow:

Important Note: The names of the methods are not arbitrary; their conventions follow specific guidelines, which I will 
explain in the next section. You'll also see types like Optional<Book> and Collection<Book>. While we could use Book 
directly, leveraging Optional and Collection aligns with the JPA specification and Spring Data's conventions, which we 
will follow to make use of their provided functionality.

Two methods in the BookService are more complex and go beyond basic CRUD operations. We'll delve into these methods in 
detail later, as our aim is to be as "lazy" as possible by leveraging the capabilities provided by JPA and Spring Data 
to their fullest.

With our services defined and the method signatures determined, we’re ready to start building the repository interfaces 
and their concrete implementations, based on these established requirements.

# Infrastructure layer - Interface adapters
Let's start by creating three controller classes: AuthorController, PublisherController, and BookController. We will use
RESTful endpoints to interact with the services we created earlier: 
```java
package tutorials.databases.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutorials.databases.domain.Author;
import tutorials.databases.service.AuthorService;

import java.util.Optional;

@RestController
@RequestMapping("/authors") 
public class AuthorController {
   private final AuthorService authorService;

   public AuthorController(AuthorService authorService) {
      this.authorService = authorService;
   }

   @PostMapping
   public ResponseEntity<Author> addAuthor(@RequestParam String name, @RequestParam String address) {
      Author author = authorService.addAuthor(name, address);
      return new ResponseEntity<>(author, HttpStatus.CREATED);
   }

   @GetMapping("/{authorId}")
   public ResponseEntity<Author> getAuthor(@PathVariable String authorId) {
      Optional<Author> author = authorService.findById(authorId);
      return author.map(ResponseEntity::ok)
              .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }

   @DeleteMapping("/{authorId}")
   public ResponseEntity<Void> deleteByAuthorId(@PathVariable String authorId) {
      authorService.deleteAuthor(authorId);
      return ResponseEntity.noContent().build();
   }
}
```
```java
package tutorials.databases.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutorials.databases.domain.Publisher;
import tutorials.databases.service.PublisherService;

import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
   private final PublisherService publisherService;

   public PublisherController(PublisherService publisherService) {
      this.publisherService = publisherService;
   }

   @PostMapping
   public ResponseEntity<Publisher> addPublisher(@RequestParam String name, @RequestParam String address) {
      Publisher publisher = publisherService.addPublisher(name, address);
      return new ResponseEntity<>(publisher, HttpStatus.CREATED);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Publisher> getPublisher(@PathVariable String id) {
      Optional<Publisher> publisher = publisherService.findById(id);
      return publisher.map(ResponseEntity::ok)
              .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletePublisher(@PathVariable String id) {
      try {
         publisherService.deletePublisher(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }
}
```
```java
package tutorials.databases.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutorials.databases.domain.Book;
import tutorials.databases.service.BookService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
   private final BookService bookService;

   public BookController(BookService bookService) {
      this.bookService = bookService;
   }

   @PostMapping
   public ResponseEntity<Book> addBook(@RequestParam String title, @RequestParam String authorId) {
      Book book = bookService.addBook(title, authorId);
      return new ResponseEntity<>(book, HttpStatus.CREATED);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Book> getBook(@PathVariable String id) {
      Optional<Book> book = bookService.findById(id);
      return book.map(ResponseEntity::ok)
              .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }

   @GetMapping("/author/{authorId}")
   public ResponseEntity<Collection<Book>> getBooksByAuthor(@PathVariable String authorId) {
      Collection<Book> books = bookService.findBooksByAuthorId(authorId);
      return ResponseEntity.ok(books);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteBook(@PathVariable String id) {
      Optional<Book> book = bookService.findById(id);
      if (book.isPresent()) {
         bookService.deleteById(id);
         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
   }

   @DeleteMapping("/author/{authorId}")
   public ResponseEntity<Void> deleteBooksByAuthor(@PathVariable String authorId) {
      boolean deleted = bookService.deleteByAuthorId(authorId);
      if (deleted) {
         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
   }
}
```
Important note: I am not worried about how well done the controllers are, as they are just a way to interact with the
services. The controllers are not the focus of this tutorial, so I am not going to spend too much time on them. We could
implement all sorts of input validation, error handling, and other features, but for now, we will keep them simple.

Now we can finally start thinking of the repositories. We will start by tackling the relational databases, as they are the
most common and the easiest to work with. 

## Creating a generic RDBMS repository using Spring Data JPA
Just as a refresher, extending JPA repositories offers some methods that will be created for us. Just for information, 
JPARepository is an interface that is the result of a chain of interfaces that starts with the Repository interface, and 
going through ListCrudRepository, ListPagingAndSortingRepository and ListJpaRepository. Each interface adds more 
methods to the previous one. 

JpaRepository is the one that adds the most functionality, so we will use that one. It is relevant to know the names of 
the methods the JPA repository offers, because they will not be declared in our custom entity repository interfaces (unless
we override to add/change functionality).

Let's then create our JPA repository interfaces for each entity:
```java
package tutorials.databases.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

public interface JpaAuthorRepository extends JpaRepository<Author, String>, AuthorRepository {

}
```
```java
package tutorials.databases.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface JpaPublisherRepository extends JpaRepository<Publisher, String>, PublisherRepository {
}
```
```java
package tutorials.databases.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

public interface JpaBookRepository extends JpaRepository<Book, String>, BookRepository {
}

```
It is normal to have three questions right now:
1. Why are we extending the JPA repository and the custom repository interface? Remember that we had to create an abstract
interface for the services to work with. This is because we want to keep the services independent of the specific repository
implementations. By extending the JPA repository, we get all the CRUD methods for free, and by extending the custom repository
interface, we ensure that the services can work with any repository implementation.

2. Why are we using the String type for the ID? It is rather common to have an Id object. This is because normally Ids 
have business/validation logic and whatnot, I am not too worried about it, and am lazy to create an Id object, so I'll just
use a String as an Id (even though I could use a UUID object, but never mind).

3. Where are the methods? The methods the JPA specifies are not declared in the custom repository interfaces. They can be
   looked at by following the chain of implementations. So you don't have to go through the names of the methods, I will show
   you some relevant ones:
- T save (T entity) - Saves an entity and returns the saved entity.
- T findById (ID id) - Retrieves an entity by its id.
- void deleteById (ID id) - Deletes an entity by its id.
- boolean existsById (ID id) - Checks if an entity with the given id exists.

Remember those two more complex methods in the BookService? Since they go beyond simple CRUD operations, we need to add 
them to the BookRepository.

Spring Data JPA can infer queries from method signatures in your repository interface. By defining these methods with the 
correct names and return types, Spring Data JPA will automatically generate the appropriate queries for you.

To do this, open your BookRepository interface. Right-click anywhere in the file and select 'Show Context Actions'. As you 
begin typing the return type of the method (e.g., Collection<Book> or Book), start with a letter like 'f' to see the suggestions 
that Spring Data JPA offers. Using these suggestions will help ensure that your methods are defined correctly and that 
Spring Data JPA generates the queries as intended. Make sure to add the following methods to the BookRepository interface:
```java
public interface JpaBookRepository extends JpaRepository<Book, String>, BookRepository{
   Collection<Book> findBooksByAuthorId (String authorId);
   boolean deleteAllByAuthorId(String authorId);
}
```
We just finished creating our repository interfaces without having to worry about concrete implementations or the specific
query methods. Spring Data JPA will take care of generating the queries based on the method signatures we defined, all at
runtime. 

Now, remember those abstract repository interfaces we created? We need to make sure
these methods are available there as well. But keep in mind we don't want to expose the JPA methods on those interfaces:
```java
package tutorials.databases.service.repositories;

import tutorials.databases.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(String id);
    void deleteById(String id);
}
```
```java
package tutorials.databases.service.repositories;

import tutorials.databases.domain.Publisher;

import java.util.Optional;

public interface PublisherRepository{
    Publisher save(Publisher publisher);
    Optional<Publisher> findById(String id);
    void deleteById(String authorId);
}
```
```java
package tutorials.databases.service.repositories;

import tutorials.databases.domain.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository{
    Book save(Book book);
    Optional<Book> findById (String id);
    void deleteById(String authorId);
    Collection<Book> findBooksByAuthorId(String authorId);
    boolean deleteAllByAuthorId(String authorId);
}
```

NOTE FROM THE FUTURE: Turns out the next bit is a terrible idea, do not start annotating your domain objects with JPA
annotations. It will work great if we only have 1 database, but if you ever need to add more, and be tempted to add more
annotations from different databases to the same class, lots of trouble will occur. Feel free to jump over to the Redis 
section where I will create the different data models and mappers, including JPA. Or you may continue with this, but be
warned.

Finally, we need to annotate our domain classes with JPA annotations. This is because we are using JPA repositories, and
JPA needs to know how to map the entities to the database. We will add the annotations to the domain classes:
```java
package tutorials.databases.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

   @Id
   private String id;

   private String name;
   private String address;
}
```
```java
package tutorials.databases.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

   @Id
   private String id;

   private String name;
   private String address;
}
```
```java
package tutorials.databases.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

   @Id
   private String id;

   private String title;
   private String genre;
   private String authorId;
   private String publisherId;
}
```

Let's quickly test this on a postgres db, in application properties, add the following:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```
Let's docker-compose up to launch the postgreSQL db (you may stop the others). Then we can run the application and open
Postman to test the endpoints:
- POST localhost:8080/authors?name=John&address=123 Main St
- GET localhost:8080/authors/{id}

Let us now close the application, swap over to MySQL, and test the application again. In application properties, add the
following:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```
Feel free to use the previous commands on postman to test the application with mySQL

## Creating a MongoDB repository using Spring Data MongoDB
MongoDB is a NoSQL database that stores data in a flexible, JSON-like format called BSON (Binary JSON). Instead of using 
tables and rows like traditional SQL databases, MongoDB uses collections and documents. 

Documents: These are individual records, similar to rows in relational databases, but they can have varying structures. 
Each document is a set of key-value pairs. Each individual entry in a MongoDB collection is a document. An example of a 
document in a collection of authors might look like this:
```json
{
   "_id": "1",
   "name": "John Doe",
   "address": "123 Main
}
```
Collections: These are "bags" of documents. They are analogous to tables in relational databases, but they don't enforce
a schema. This means individual documents in a collection can have different fields and structures.

To perform operations on MongoDB, we use several commands, such as find, insert, update, and delete. MongoDB also provides
a query language that allows for complex queries and aggregations. We will not go into detail about MongoDB's query language
in this tutorial, but we will demonstrate how to interact with MongoDB using Spring Data MongoDB, which will abstract away
many of the complexities of working with MongoDB directly. Our goal really is to make sure that when we hit postman, the
request is processed and the response is returned, and its the same for all databases.

Considering we have most of the work done already, we will only need to create the MongoDB repository interface and let
Spring Data MongoDB handle the rest. Let's create the MongoDB repository interfaces for each entity:
```java

package tutorials.databases.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Author;

public interface MongoAuthorRepository extends MongoRepository<Author, String>, AuthorRepository {
}
```
```java
package tutorials.databases.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Publisher;

public interface MongoPublisherRepository extends MongoRepository<Publisher, String>, PublisherRepository {
}
```
```java
package tutorials.databases.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Book;

public interface MongoBookRepository extends MongoRepository<Book, String>, BookRepository {
}
```
You might be tempted to check what MongoRepository offers and will quickly find out it actually implements the 
ListCrudRepository<T, ID>, ListPagingAndSortingRepository and ListQueryByExampleExecutor<T> interfaces. This means that,
like with JPA, we get all the CRUD methods for free. We just need to make sure the "complex" methods are available in the
MongoBookRepository interface:
```java
Collection<Book> findBooksByAuthorId (String authorId);
boolean deleteAllByAuthorId(String authorId);
```
You will also notice that these methods are the same as the ones we created for the JPA repository. This is because we 
are still being helped by Spring Data to generate the queries for us. And regardless if it is a SQL or NoSQL database, the
methods are the same at this level.

Which means we are done setting up the MongoDB repository. We can now test the application with MongoDB. In application
properties, add the following:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/mydb
```

## Dealing with multiple databases - DEVOPS
When reaching this part one could easily despair as it turns out springboot does quite a lot in the background, often
overwriting configurations and whatnot. But fear not, we can still use multiple databases. I will try to vaguely explain
what issues can arise here, and how I managed to solve them, which is probably not the most elegant way, but what is life
without a bit of danger?

You probably reading this gradually, but this step actually brought the whole project to a halt for quite some time, and
highlights my personal inexperience with springboot. Nonetheless, diving hard into springboot is definetly a future requirement
but I find it out of scope for this project.

Anyway let me try and explain a couple of things I found out after a lot of trial and error:
1. Springboot autoconfigures beans based on a lot of factors, as we know. What I didn't know is that the simple presence
of a dependency in the pom.xml file can trigger springboot to autoconfigure a bean. Which is quite upsetting as without
proper ways of logging which beans are being created, it is hard to know what is going on. So first thing is to make sure
you are aware of what beans are being created. To do this we can include in application.properties:
```properties
logging.level.org.springframework.beans.factory.support=DEBUG
```
This is soooo crucial, as it tells you exactly what is being produced and what is being excluded.
2. To stop springboot from dynamically autoconfiguring datasources and the actual repositories so we need to add:
```properties
spring.autoconfigure.exclude=\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```
3. You will notice there are multiple entries for mongo. With JPA we can suppress the autoconfiguration of the repositories
with just 1 command, but with mongoDB we need to add multiple entries. This is because springboot has multiple configurations
for mongoDB, and we need to exclude all of them. This is a bit of a pain, but it is what it is. 
4. Still regarding mondoDB, because we are excluding more than 1 bean, we have to make sure that it is reactivated when we
want. Obviously one being unknown to this sorcery would think that just enabling @MongoRepository would be enough, but no.
5. Creating specific configuration files and utilizing configuration annotation. Feel free to just copy the classes and put them 
at the main repositories package:
```java
package tutorials.databases.repositories;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tutorials.databases.repositories.jpa.JpaAuthorRepository;
import tutorials.databases.repositories.jpa.JpaBookRepository;
import tutorials.databases.repositories.jpa.JpaPublisherRepository;

@Configuration
@ConditionalOnProperty(value = "database.type", havingValue = "jpa")
@EnableJpaRepositories(basePackages = "tutorials.databases.repositories.jpa")
public class JpaRepositoryConfiguration {

   @Bean
   public AuthorRepository jpaAuthorRepository(JpaAuthorRepository jpaAuthorRepository) {
      return jpaAuthorRepository;
   }

   @Bean
   public BookRepository jpaBookRepository(JpaBookRepository jpaBookRepository) {
      return jpaBookRepository;
   }

   @Bean
   public PublisherRepository jpaPublisherRepository(JpaPublisherRepository jpaPublisherRepository) {
      return jpaPublisherRepository;
   }
}
```
```java
package tutorials.databases.repositories;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import tutorials.databases.repositories.mongo.MongoAuthorRepository;
import tutorials.databases.repositories.mongo.MongoBookRepository;
import tutorials.databases.repositories.mongo.MongoPublisherRepository;

@Configuration
@ConditionalOnProperty(value = "database.type", havingValue = "mongo")
@EnableMongoRepositories(basePackages = "tutorials.databases.repositories.mongo")
public class MongoRepositoryConfiguration extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mydb"; // Replace with your actual database name
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017"); // Replace with your MongoDB URI
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Bean
    public AuthorRepository mongoAuthorRepository(MongoAuthorRepository mongoAuthorRepository) {
        return mongoAuthorRepository;
    }

    @Bean
    public BookRepository mongoBookRepository(MongoBookRepository mongoBookRepository) {
        return mongoBookRepository;
    }

    @Bean
    public PublisherRepository mongoPublisherRepository(MongoPublisherRepository mongoPublisherRepository) {
        return mongoPublisherRepository;
    }
}
```
We will then place this in application.properties (mongo is an example, it can be jpa):
```properties
database.type=mongo
```
What these two classes do is basically use the @Configuration annotation to signal that these classes are configuration classes.
The @ConditionalOnProperty annotation is telling springboot that, if the property database.type is mongo for example, it
will tell springboot to load all the beans contained in the defined package. This way, when springboot loads, it checks
the variable then only creates beans related to the database we choose. This is a very important step, as it allows us to
use multiple databases without springboot going crazy.

Inside these classes we define the beans that we want to create (or reactivate), and we can use the @Bean annotation to 
create them. But now we must know what to signal as beans. And as we can see by the mongoDB one, sometimes they can get
quite complex. But this is what LLMs are for.

6. One last comment. I had some trouble with the database.type entry in application.properties. This is because springboot
had some trouble reading the property. One way to solve this problem, is to manually add that property unto the additional
springboot configuration metadata file. This is done by creating a file called spring-configuration-metadata-whatevername.properties
and slaping that in there. If you use IntelliJ you can just Alt + click on the property and click on "define configuration
key". This will create the Json file for you, and if not available, the whole META-INF package inside resources.

7. Topics for later study: how springboot scans for beans, how to create custom beans, how to exclude beans, how to use
conditional annotations, how to use configuration annotations, how to use springboot configuration metadata files.
Qualifiers!!!!

## Creating a Cassandra repository using Spring Data Cassandra
Cassandra is a wide-column NoSQL database designed to handle large amounts of data with high performance. Originally 
developed by Facebook, it is now maintained by the Apache Software Foundation. Each instance of Cassandra is a node in 
a cluster, typically storing between 2-4 TB of data. Nodes can be easily distributed to scale horizontally, with each 
node having equal read/write capabilities and being responsible for a subset of the data (known as a partition).

Nodes are organized into clusters, and data can be programmatically replicated across multiple nodes to ensure fault 
tolerance. This architecture eliminates any single point of failure, ensuring that data is always available.

In Cassandra, keyspaces are the highest level of data organization, similar to databases in traditional RDBMS. Within 
each keyspace, data is organized into tables called column families. Unlike traditional databases, these tables are 
schema-less, allowing each row to have a different number of columns and to store unstructured data.

Interaction with Cassandra is facilitated through the Cassandra Query Language (CQL), which is similar to SQL but with 
some differences. CQL is used to create keyspaces, tables, and perform CRUD operations. However, Cassandra does not 
support joins, subqueries, or transactions. Instead, data is often denormalized and stored in a way that aligns with 
specific use cases to optimize retrieval speed.

To write data to Cassandra, you need to create a session object that connects to the Cassandra cluster. This session
object is used to execute CQL queries and interact with the database. You can also use the DataStax Java driver to
simplify the process of connecting to Cassandra and executing queries. The driver provides a higher-level API for
interacting with Cassandra, handling connection pooling, load balancing, and other low-level details.

To interact with Cassandra in a Spring Boot application, we can use Spring Data Cassandra, which provides a higher-level
abstraction for working with Cassandra databases. Spring Data Cassandra simplifies the process of connecting to Cassandra,
executing queries, and mapping the results to Java objects. It also provides support for creating repositories that
perform CRUD operations on Cassandra entities. Include the following dependencies in your project:
```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-cassandra</artifactId>
     </dependency>
```
In application.properties let's change the db type to cassandra, and place the following properties:
```properties
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration

database.type=cassandra

spring.cassandra.keyspace-name=keyspace1
spring.cassandra.contact-points=localhost:9042
spring.cassandra.port=9042
spring.cassandra.local-datacenter=datacenter1
spring.cassandra.schema-action=recreate
```
Firstly the top 2 commands will stop springboot from autoconfiguring the cassandra beans, add them to the end of the previous
ones. The properties are pretty straightforward, but I will explain them anyway. The keyspace name is the name of the 
keyspace you want to use. The contact points are the addresses of the nodes in the cluster. The port is the port number 
of the nodes. The local datacenter is the name of the datacenter where the application is running. The schema action is 
the action to take when the application starts.

These are suggested properties. Recreate is like create-drop in JPA, it will drop the keyspace and recreate it every time.

Let's create a new repository for each of our entities, similar to what we did for MongoDB and JPA, but now we will
use Spring Data Cassandra:
```java
package tutorials.databases.repositories.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

public interface CassandraAuthorRepository extends CassandraRepository<Author, String>, AuthorRepository {
}
```
```java
package tutorials.databases.repositories.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface CassandraPublisherRepository extends CassandraRepository<Publisher, String>, PublisherRepository {
}
```
```java
package tutorials.databases.repositories.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

import java.util.Collection;

public interface CassandraBookRepository extends CassandraRepository<Book, String>, BookRepository {
   Collection<Book> findBooksByAuthorId (String authorId);
   boolean deleteAllByAuthorId(String authorId);
}
```
Again, spring data cassandra will generate the queries for us, so we don't have to worry about that.

Finally we need to sort out our configuration files. We will create a new configuration file for Cassandra, similar to
what we did for MongoDB:
```java
package tutorials.databases.repositories.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.cassandra.CassandraAuthorRepository;
import tutorials.databases.repositories.cassandra.CassandraBookRepository;
import tutorials.databases.repositories.cassandra.CassandraPublisherRepository;

@Configuration
@ConditionalOnProperty(value = "database.type", havingValue = "cassandra")
@EnableCassandraRepositories(basePackages = "tutorials.databases.repositories.cassandra")
public class CassandraRepositoryConfiguration extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "keyspace1"; // Replace with your actual keyspace name
    }

    @Bean
    public AuthorRepository cassandraAuthorRepository(CassandraAuthorRepository cassandraAuthorRepository) {
        return cassandraAuthorRepository;
    }

    @Bean
    public BookRepository cassandraBookRepository(CassandraBookRepository cassandraBookRepository) {
        return cassandraBookRepository;
    }

    @Bean
    public PublisherRepository cassandraPublisherRepository(CassandraPublisherRepository cassandraPublisherRepository) {
        return cassandraPublisherRepository;
    }
}
```

NOTE FROM THE FUTURE: Do not do what I am about to tell you. I've repeated this a few times during this project. But in case
you are jumping around and not reading it as a whole, go for the data model and mapper approach I mention during the Redis
section. Over-annotating the domain classes will lead to a lot of trouble with springboot. Lets do the best practice and
keep the domain classes clean.

Next, like with JPA we need to annotate the domain classes with the appropriate annotations. I have kept the annotations
for JPA but added the Cassandra ones as well:
```java
package tutorials.databases.domain;

import jakarta.persistence.Entity; // JPA
import jakarta.persistence.Id; // JPA
import org.springframework.data.cassandra.core.mapping.Column; // Cassandra
import org.springframework.data.cassandra.core.mapping.PrimaryKey; // Cassandra
import org.springframework.data.cassandra.core.mapping.Table; // Cassandra
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA
@Table("author") // Cassandra
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id // JPA
    @PrimaryKey // Cassandra
    private String id;

    @Column("name") // Cassandra
    private String name;

    @Column("address") // Cassandra
    private String address;
}
```
```java
package tutorials.databases.domain;

import jakarta.persistence.Entity; // JPA
import jakarta.persistence.Id; // JPA
import org.springframework.data.cassandra.core.mapping.Column; // Cassandra
import org.springframework.data.cassandra.core.mapping.PrimaryKey; // Cassandra
import org.springframework.data.cassandra.core.mapping.Table; // Cassandra
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA
@Table("book") // Cassandra
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id // JPA
    @PrimaryKey // Cassandra
    private String id;

    @Column("title") // Cassandra
    private String title;

    @Column("genre") // Cassandra
    private String genre;

    @Column("author_id") // Cassandra
    private String authorId;

    @Column("publisher_id") // Cassandra
    private String publisherId;
}
```
```java
package tutorials.databases.domain;

import jakarta.persistence.Entity; // JPA
import jakarta.persistence.Id; // JPA
import org.springframework.data.cassandra.core.mapping.Column; // Cassandra
import org.springframework.data.cassandra.core.mapping.PrimaryKey; // Cassandra
import org.springframework.data.cassandra.core.mapping.Table; // Cassandra
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA
@Table("publisher") // Cassandra
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

    @Id // JPA
    @PrimaryKey // Cassandra
    private String id;

    @Column("name") // Cassandra
    private String name;

    @Column("address") // Cassandra
    private String address;
}
```
Next in the order of business is something I will research if I ever have to use Cassandra in production. I am not sure, 
but I am being led to believe we have to preconfigure the keyspace and tables in Cassandra. To avoid faffing around with 
this, I will just create the keyspace and tables manually. I am sure there will be more dynamic way to do this, like 
triggering a script on app start or on database docker setup, but I really do not want to waste much more time on this.

To create those we will open docker desktop and enter the Cassandra shell. We do this by clicking our running Cassandra
container and clicking on the EXEC tab. We then enter the following command:
```bash
cqlsh
```
This will open the Cassandra shell. We then enter the following commands:
```cql
CREATE KEYSPACE keyspace1 WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};
USE keyspace1;
CREATE TABLE author (id text PRIMARY KEY, name text, address text);
CREATE TABLE book (id text PRIMARY KEY, title text, genre text, author_id text, publisher_id text);
CREATE TABLE publisher (id text PRIMARY KEY, name text, address text);
```
You can check they have been done by using the describe command:
```cql
DESCRIBE keyspaces;
DESCRIBE TABLES;
```
        
With this done we can go over to postman and test the application. We can use the same commands as before:
- POST localhost:8080/authors?name=John&address=123 Main St
- GET localhost:8080/authors/{id}

## Notes on the ordeal of creating a dynamic environment vs the autoconfigured beans
It appears that the fact we have placed the annotations on the domain classes is enough for springboot to ask for 
Cassandra beans when loading the application, even if they are not meant to be used anywhere. This obviously brings a lot 
of confusion when trying to use another database. The only way we can swap back to another one, without springboot shouting 
for the lack of cassandra beans is by removing the annotations from the domain classes. I have spent far too much time on 
this, and have reached my current level of frustration with trying to get this to work. To avoid sacrificing the 
continuation of the whole project, I have decided to comment out all the Cassandra related stuff, including the annotations 
on the domain classes and from POM. 

In the meantime I have managed to dynamically change JPA and Mongo by adopting profiles and commenting/uncommenting the
datasource properties in application.properties. This is the new application.properties:

```properties
spring.application.name=databases
spring.main.allow-bean-definition-overriding=true
logging.level.org.springframework.boot.autoconfigure=DEBUG
# To change databases:
# 1.
# Change the following value to the desired one (jpa/mongo/cassandra/redis)
spring.profiles.active=jpa

# 2.
# JPA - uncomment spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration otherwise it wont load entity manager
# MongoDB - comment spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration otherwise it will expect a relational datasource...

# spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

# 3.
# Uncomment the desired database configuration
#Postgres
#spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
#spring.datasource.username=postgres
#spring.datasource.password=password
#spring.jpa.hibernate.ddl-auto=create-drop
#spring.jpa.show-sql=true

#MySQL
#spring.datasource.url=jdbc:mysql://localhost:3306/mydb
#spring.datasource.username=root
#spring.datasource.password=password
#spring.jpa.hibernate.ddl-auto=create-drop
#spring.jpa.show-sql=true

#MongoDB
#spring.data.mongodb.uri=mongodb://localhost:27017/mydb
```
I've added @Profile("mongo") on all mongoDB repositories and @Profile("jpa") on all JPA repositories:
```java
package tutorials.databases.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

@Profile("mongo")
public interface MongoPublisherRepository extends MongoRepository<Publisher, String>, PublisherRepository {
}
```
```java
package tutorials.databases.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

@Profile("jpa")
public interface JpaAuthorRepository extends JpaRepository<Author, String>, AuthorRepository {
}
```
Finally I swapped the @ConditionalOnProperty annotation for @Profile on the configuration classes:
```java
@Configuration
@Profile("jpa")
@EnableJpaRepositories(basePackages = "tutorials.databases.repositories.jpa")
public class JpaRepositoryConfiguration {
```
```java
@Configuration
@Profile("mongo")
@EnableMongoRepositories(basePackages = "tutorials.databases.repositories.mongo")
public class MongoRepositoryConfiguration extends AbstractMongoClientConfiguration {
```
To resolve the issues with wild cassandra beans I commented everything related to Cassandra, from the config files, to 
the repositories, pom and annotations from domain classes. If we ever want to turn on Cassandra again, we have to 
uncomment everything, probably suppress the Cassandra beans again, and let the configuration file do its magic. Plus 
set up the keyspace and tables manually, or find a better way to go about it.

Curiously enough, all this made me wonder why MongoDB had no problem with annotations on the domain class, which made me realize
I never used any, and it still worked, weird. Apparently we should have used @Document on the domain classes, but it still
worked without it. Perhaps mongoDB was giving me a free pass, but Cassandra was not having it. We can't live without them though.

After the devastating defeat with Cassandra, I decided to continue with the project but have made my decision on what to study 
next - Springboot, and in proper depth m8.

## Creating a Redis repository using Spring Data Redis
Redis (Remote Dictionary Server) is a high-performance, in-memory data structure store widely used as both a cache and a 
database. Unlike traditional databases that primarily store data on disk, Redis keeps data in memory, which allows for 
sub-millisecond response times and high throughput. While Redis is often used as a cache due to its ability to quickly 
retrieve and store data, it also serves as a persistent data store, with the capability to periodically save data to disk. 
This dual nature makes Redis a versatile tool in many high-performance application architectures.

Data in Redis is stored as key-value pairs, and the system supports a variety of data structures, including strings, lists, 
sets, sorted sets, hashes, bitmaps and geospatial indexes. For example, strings are often used to store 
simple values like user session data, while hashes can store more complex objects like user profiles. Redis also supports 
atomic operations on these data types, meaning operations like incrementing a value or adding an item to a list are 
completed fully or not at all, ensuring data consistency.

Persistence in Redis is achieved through two main mechanisms: RDB (Redis Database Backup) and AOF (Append-Only File). 
RDB snapshots the dataset at specified intervals, while AOF logs every write operation, offering a more durable and 
resilient option in case of a system crash. These mechanisms allow Redis to provide durability while still maintaining 
its performance advantages.

Redis's functionality can be further extended through modules, which are plugins that add specialized capabilities. 
Popular modules include RedisJSON, which enables JSON document support, and RedisTimeSeries, which adds time series data 
management features. These modules allow Redis to adapt to specific business needs, making it a flexible tool for a wide 
range of applications.

As with the previous, Redis has Spring Data support, so let's get it set up:
```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-redis</artifactId>
     </dependency>
```
In application.properties let's change the db type to redis:
```properties
spring.profiles.active=redis
spring.data.redis.host=redis-db
spring.data.redis.port=6379
```

Regarding the set up of redis repositories it is to note that there isn't actually a specific repository we extend for Redis,
like we had with JPA, MongoDB and Cassandra. To obtain the methods, we will simply extend the CRUD repository :
```java
package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.domain.Author;
import tutorials.databases.repositories.AuthorRepository;

public interface RedisAuthorRepository extends CrudRepository<Author, String>, AuthorRepository {
}
```
```java
package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.PublisherRepository;

public interface RedisPublisherRepository extends CrudRepository<Publisher, String>, PublisherRepository { 
}
```
```java
package tutorials.databases.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import tutorials.databases.domain.Book;
import tutorials.databases.repositories.BookRepository;

public interface RedisBookRepository extends CrudRepository<Book, String>, BookRepository {
    
}
```
IMPORTANT NOTE: Redis is a key-value store, and its querying capabilities are not as sophisticated as those of a relational 
database or even a document store like MongoDB. This means the two extra methods that we added on the book repositories,
that were covered by spring data, will not work for Redis. Considering our goal was to have the same methods for all databases,
while letting spring data deal with the programming aspect of it, we will have to remove these methods from the Redis repository
and count this as an L for Redis. Of course, we could absolutely sort out these methods by pulling the data from the database
and then filtering it in the application, but this is not the goal of this project.

Moving on, Spring data is not completely useless with Redis, as it still provides a lot of functionality, and we can still
use the CRUD repository to perform basic operations. 

Finally, we need to sort out the configuration files. We will create a new configuration file for Redis, similar to what we did
for MongoDB and Cassandra:
```java
package tutorials.databases.repositories.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;
import tutorials.databases.repositories.PublisherRepository;
import tutorials.databases.repositories.redis.RedisAuthorRepository;
import tutorials.databases.repositories.redis.RedisBookRepository;
import tutorials.databases.repositories.redis.RedisPublisherRepository;

@Configuration
@Profile("redis")
@EnableRedisRepositories(basePackages = "tutorials.databases.repositories.redis")
public class RedisRepositoryConfiguration {

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }

   @Bean
   public AuthorRepository redisAuthorRepository(RedisAuthorRepository redisAuthorRepository) {
      return redisAuthorRepository;
   }

   @Bean
   public BookRepository redisBookRepository(RedisBookRepository redisBookRepository) {
      return redisBookRepository;
   }

   @Bean
   public PublisherRepository redisPublisherRepository(RedisPublisherRepository redisPublisherRepository) {
      return redisPublisherRepository;
   }
}
```

## Evolving our architecture - Domain models to specific data models
Looking back at the issues we had with Cassandra, and the mess created by over-annotating our domain classes for different 
databases, it’s clear we need a new approach. Trying to fit multiple database requirements into a single domain model has 
been problematic, so it’s time to rethink our strategy.

Creating separate data models for each database might seem like a lot of work, but it looks like the most practical solution 
for now. I tried to find ways to conditionally apply annotations to avoid this duplication, but no good solutions came up. 
So, it seems we’re stuck with the task of creating dedicated data models for each database and domain object.

Next, we need to figure out how to map these data models to our domain objects. This means setting up mappers that handle 
the conversion between the domain models and the database-specific models. We’ll need some way to decide which mapper to 
use, probably by adding some conditional logic in our service layer. Spring’s @Profile annotation might help with this.

So, here’s the plan: we’ll create a separate data model for each database and a corresponding mapper for each domain object. 
We’ll adjust our service layer to use the correct mapper based on the database in use. If this works out, it might even make 
it possible to give Cassandra another shot.

## Understanding data models
Before we dive into creating data models and mappers, let’s take a moment to understand what they are and why they’re
important. Data models define how the data is organized, stored, and accessed within a database system. In essence, they 
are "objects", that are built in an object-oriented way (in our case), that serve as a blueprint for how we want data to be 
structured in the database. Considering each database has an api, and we have frameworks like Spring Data to help us, we
can create these objects in a way that they can be easily mapped to the database. Most of the time, these objects are
simple POJOs with specific annotations that tell the framework how to map them to the database and vice versa.

## Constructing data models for each database: jpa, mongoDB, cassandra and redis
Let's start by removing all the annotations from our domain classes. 

We will then create a new package called data models. For added clarity, we will create a subpackage for each kind of 
object (arguably we can do it by database, but turns out I have more database support than objects!). For instance
in the author package we will have: AuthorCassandraDataModel, AuthorJpaDataModel, AuthorMongoDataModel and AuthorRedisDataModel.
Continue the same rationale for the other objects.

## The architectural strategy for the data models
We want our service to work with every database. We also do not want our service to care for choosing which supporting
structures to use (our mappers, our repositories, our datamodels). This poses quite an interesting challenge that can be
sorted using the good old interface segregation principle. We could make our mappers static, but then the service would 
have to know which one to pick, but if we give springboot the responsibility of creating the mappers, we can use the 
@Profile annotation to only create the ones we want. This way, the service will only have to call an abstract mapper
interface, and springboot will provide the correct one. 

To do this we have to create an interface which I called a general mapper. This mapper will be injected dynamically by
springboot at the constructor level. Now we need to create a mapper implementation for each database. Arguably, we could
place another layer of abstraction, this was if we ever wanted to make another mapper for the same database, but for now
we will keep it like this. 

So we have our general interface that gets injected in our service; we have 4 mapper concrete implementations, one for each
database, and flagged with the appropriate @Profile annotation; We now need to actually create the mapper logic for each.

Now we are set on our mapper strategy, we need to understand how we are tackling the data models with appropriate polymorphism.
Considering we could have a data model for each database, per domain object. We would then have for instance: 1 AuthorJPADataModel,
1 AuthorMongoDataModel, 1 AuthorCassandraDataModel and 1 AuthorRedisDataModel. Considering we want to keep the service as
clean as possible, we will have to create a general data model interface, otherwise the service would have to be prepared
to receive either a JPA, Mongo, Cassandra or Redis data model. 

Now to solve this conundrum we will create a general data model interface, and then create a concrete implementation for each.
For instance, our AuthorDataModel interface will have 4 concrete implementations: AuthorJPADataModel, AuthorMongoDataModel,
AuthorCassandraDataModel and AuthorRedisDataModel. And now the rest is up to our mappers to apply their protected variation
goodness (I think its protected variation??). 

Back to the mappers, our methods will have to receive the domain object and return a general data model object, whilst
inside the method we will have to cast the domain object to the specific data model object. 

## Continuing with the data models
Let's create the data model interfaces. Let us not worry about the methods yet:
```java
package tutorials.databases.datamodels.author;

public interface AuthorDataModel {
}
```
```java
package tutorials.databases.datamodels.book;

public interface PublisherDataModel {
}
```
```java
package tutorials.databases.datamodels.publisher;

public interface BookDataModel {
}
```
Now let's create the JPA data models:

```java
package tutorials.databases.datamodels.author;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorJPADataModel implements AuthorDataModel {

   @Id
   private String id;
   private String name;
   private String address;
}
```

```java
package tutorials.databases.datamodels.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;

@Entity
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherJPADataModel implements PublisherDataModel {

   @Id
   private String id;
   private String name;
   private String address;
}
```

```java
package tutorials.databases.datamodels.publisher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookJPADataModel implements BookDataModel {

   @Id
   private String id;
   private String title;
   private String genre;
   private String authorId;
   private String publisherId;
}
```
Note on JPA annotations: I will provide just a brief explanation of SOME of the annotations used in the JPA data models.
@Entity: This annotation marks the class as an entity, which means it will be mapped to a database table.
@Table: This annotation specifies the name of the table to which the entity is mapped. If not specified, the table name 
will be the same as the class name (calling it BookJPADataModel would create a table called book_jpa_data_model).
@Id: This annotation specifies the primary key of the entity. It can be applied to a field or property of the entity class.
There are others like @GeneratedValue, @Column, @JoinColumn, @OneToMany, @ManyToOne, @ManyToMany, @OneToOne, @JoinTable, etc.
JPA can also manage the id generation for you, and you can specify the strategy with @GeneratedValue(strategy = GenerationType.AUTO).

Now onwards to the mongo data models:

```java
package tutorials.databases.datamodels.author;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;

@Document(collection = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorMongoDataModel implements AuthorDataModel {

   @Id
   private String id;

   private String name;

   private String address;
}
```

```java
package tutorials.databases.datamodels.book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;

@Document(collection = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherMongoDataModel implements PublisherDataModel {

   @Id
   private String id;

   private String name;

   private String address;
}
```

```java
package tutorials.databases.datamodels.publisher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMongoDataModel implements BookDataModel {

   @Id
   private String id;

   private String title;

   private String genre;

   private String authorId;

   private String publisherId;
}
```
Notes on MongoDB annotations (there are loads of options): 
@Document: This annotation marks the class as a document, which means it will be mapped to a collection in MongoDB. 
@Id: This annotation specifies the primary key of the document. It can be applied to a field or property of the document class.
There are other annotations like @Field, @Indexed, @DBRef, @TextIndexed, @GeoSpatialIndexed, @CompoundIndex, etc. 

Moving on to Cassandra data models:

```java
package tutorials.databases.datamodels.author;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;

@Table("authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCassandraDataModel implements AuthorDataModel {

   @PrimaryKey
   private String id;

   @Column("name")
   private String name;

   @Column("address")
   private String address;
}
```

```java
package tutorials.databases.datamodels.book;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;

@Table("publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherCassandraDataModel implements PublisherDataModel {

   @PrimaryKey
   private String id;

   @Column("name")
   private String name;

   @Column("address")
   private String address;
}
```

```java
package tutorials.databases.datamodels.publisher;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;

@Table("books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCassandraDataModel implements BookDataModel {

   @PrimaryKey
   private String id;

   @Column("title")
   private String title;

   @Column("genre")
   private String genre;

   @Column("author_id")
   private String authorId;

   @Column("publisher_id")
   private String publisherId;
}
```
Notes on Cassandra annotations:
@Table: This annotation specifies the name of the table to which the entity is mapped. If not specified, the table name
will be the same as the class name (calling it BookCassandraDataModel would create a table called book_cassandra_data_model).
@PrimaryKey: This annotation specifies the primary key of the entity. It can be applied to a field or property of the entity class.
@Column: This annotation specifies the column name to which the field or property is mapped. If not specified, the column name
will be the same as the field or property name. We could arguably leave this out, but it is good practice to specify it.

Finally, let's create the Redis data models:

```java
package tutorials.databases.datamodels.author;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;

@RedisHash("authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRedisDataModel implements AuthorDataModel {

   @Id
   private String id;

   private String name;

   private String address;
}
```

```java
package tutorials.databases.datamodels.book;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;

@RedisHash("publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRedisDataModel implements PublisherDataModel {

   @Id
   private String id;

   private String name;

   private String address;
}
```

```java
package tutorials.databases.datamodels.publisher;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;

@RedisHash("books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRedisDataModel implements BookDataModel {

   @Id
   private String id;

   private String title;

   private String genre;

   private String authorId;

   private String publisherId;
}
```
Notes on Redis annotations:
@RedisHash: This annotation marks the class as a Redis hash, which means it will be stored as a hash in Redis.
@Id: This annotation specifies the primary key of the hash. It can be applied to a field or property of the hash class.

## Finishing the data model interfaces
Now that we have our concrete implementations, we can finish the interfaces. We will add the methods that will be used by
the mappers to convert the domain objects to the data models. Considering we are using lombok, we basically want to
replicate the getters and setters:
```java
package tutorials.databases.datamodels.author;

public interface AuthorDataModel {
    String getId();
    void setId(String id);

    String getName();
    void setName(String name);

    String getAddress();
    void setAddress(String address);
}
```
```java
package tutorials.databases.datamodels.book;

public interface PublisherDataModel {
    String getId();
    void setId(String id);

    String getName();
    void setName(String name);

    String getAddress();
    void setAddress(String address);
}
```
```java
package tutorials.databases.datamodels.publisher;

public interface BookDataModel {
    String getId();
    void setId(String id);

    String getTitle();
    void setTitle(String title);

    String getGenre();
    void setGenre(String genre);

    String getAuthorId();
    void setAuthorId(String authorId);

    String getPublisherId();
    void setPublisherId(String publisherId);
}
```

## Creating the mappers
I will create 1 mapper for each database, and they will implement the general mapper interface and have methods to cover
all the domain objects. For JPA:

```java
package tutorials.databases.mappers;

import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.repositories.datamodels.author.AuthorJPADataModel;
import tutorials.databases.repositories.datamodels.book.PublisherJPADataModel;
import tutorials.databases.repositories.datamodels.publisher.BookJPADataModel;
import org.springframework.stereotype.Component;

@Component
@Profile("jpa")
public class JpaMapperImpl implements GeneralMapper {

   public AuthorDataModel toAuthorDataModel(Author author) {
      if (author == null) {
         return null;
      }
      return new AuthorJPADataModel(
              author.getId(),
              author.getName(),
              author.getAddress()
      );
   }

   public Author toAuthor(AuthorDataModel authorDataModel) {
      if (authorDataModel == null) {
         return null;
      }
      return new Author(
              authorDataModel.getId(),
              authorDataModel.getName(),
              authorDataModel.getAddress()
      );
   }

   public PublisherDataModel toPublisherDataModel(Publisher publisher) {
      if (publisher == null) {
         return null;
      }
      return new PublisherJPADataModel(
              publisher.getId(),
              publisher.getName(),
              publisher.getAddress()
      );
   }

   public Publisher toPublisher(PublisherDataModel publisherDataModel) {
      if (publisherDataModel == null) {
         return null;
      }
      return new Publisher(
              publisherDataModel.getId(),
              publisherDataModel.getName(),
              publisherDataModel.getAddress()
      );
   }

   public BookDataModel toBookDataModel(Book book) {
      if (book == null) {
         return null;
      }
      return new BookJPADataModel(
              book.getId(),
              book.getTitle(),
              book.getGenre(),
              book.getAuthorId(),
              book.getPublisherId()
      );
   }

   public Book toBook(BookDataModel bookDataModel) {
      if (bookDataModel == null) {
         return null;
      }
      return new Book(
              bookDataModel.getId(),
              bookDataModel.getTitle(),
              bookDataModel.getGenre(),
              bookDataModel.getAuthorId(),
              bookDataModel.getPublisherId()
      );
   }
}
```
For mongoDB:

```java
package tutorials.databases.mappers;

import tutorials.databases.repositories.datamodels.author.AuthorMongoDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherMongoDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookMongoDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import org.springframework.stereotype.Component;

@Component
@Profile("mongo")
public class MongoMapperImpl implements GeneralMapper {

   public AuthorDataModel toAuthorDataModel(Author author) {
      if (author == null) {
         return null;
      }
      return new AuthorMongoDataModel(
              author.getId(),
              author.getName(),
              author.getAddress()
      );
   }

   public Author toAuthor(AuthorDataModel authorDataModel) {
      if (authorDataModel == null) {
         return null;
      }
      return new Author(
              authorDataModel.getId(),
              authorDataModel.getName(),
              authorDataModel.getAddress()
      );
   }

   public PublisherDataModel toPublisherDataModel(Publisher publisher) {
      if (publisher == null) {
         return null;
      }
      return new PublisherMongoDataModel(
              publisher.getId(),
              publisher.getName(),
              publisher.getAddress()
      );
   }

   public Publisher toPublisher(PublisherDataModel publisherDataModel) {
      if (publisherDataModel == null) {
         return null;
      }
      return new Publisher(
              publisherDataModel.getId(),
              publisherDataModel.getName(),
              publisherDataModel.getAddress()
      );
   }

   public BookDataModel toBookDataModel(Book book) {
      if (book == null) {
         return null;
      }
      return new BookMongoDataModel(
              book.getId(),
              book.getTitle(),
              book.getGenre(),
              book.getAuthorId(),
              book.getPublisherId()
      );
   }

   public Book toBook(BookDataModel bookDataModel) {
      if (bookDataModel == null) {
         return null;
      }
      return new Book(
              bookDataModel.getId(),
              bookDataModel.getTitle(),
              bookDataModel.getGenre(),
              bookDataModel.getAuthorId(),
              bookDataModel.getPublisherId()
      );
   }
}
```
For Cassandra:

```java
package tutorials.databases.mappers;

import tutorials.databases.repositories.datamodels.author.AuthorCassandraDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherCassandraDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookCassandraDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import org.springframework.stereotype.Component;

@Component
@Profile("cassandra")
public class CassandraMapperImpl implements GeneralMapper {

   public AuthorDataModel toAuthorDataModel(Author author) {
      if (author == null) {
         return null;
      }
      return new AuthorCassandraDataModel(
              author.getId(),
              author.getName(),
              author.getAddress()
      );
   }

   public Author toAuthor(AuthorDataModel authorDataModel) {
      if (authorDataModel == null) {
         return null;
      }
      return new Author(
              authorDataModel.getId(),
              authorDataModel.getName(),
              authorDataModel.getAddress()
      );
   }

   public PublisherDataModel toPublisherDataModel(Publisher publisher) {
      if (publisher == null) {
         return null;
      }
      return new PublisherCassandraDataModel(
              publisher.getId(),
              publisher.getName(),
              publisher.getAddress()
      );
   }

   public Publisher toPublisher(PublisherDataModel publisherDataModel) {
      if (publisherDataModel == null) {
         return null;
      }
      return new Publisher(
              publisherDataModel.getId(),
              publisherDataModel.getName(),
              publisherDataModel.getAddress()
      );
   }

   public BookDataModel toBookDataModel(Book book) {
      if (book == null) {
         return null;
      }
      return new BookCassandraDataModel(
              book.getId(),
              book.getTitle(),
              book.getGenre(),
              book.getAuthorId(),
              book.getPublisherId()
      );
   }

   public Book toBook(BookDataModel bookDataModel) {
      if (bookDataModel == null) {
         return null;
      }
      return new Book(
              bookDataModel.getId(),
              bookDataModel.getTitle(),
              bookDataModel.getGenre(),
              bookDataModel.getAuthorId(),
              bookDataModel.getPublisherId()
      );
   }
}
```
For Redis:

```java
package tutorials.databases.mappers;

import tutorials.databases.repositories.datamodels.author.AuthorRedisDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherRedisDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookRedisDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import org.springframework.stereotype.Component;

@Component
@Profile("redis")
public class RedisMapperImpl implements GeneralMapper {

   public AuthorDataModel toAuthorDataModel(Author author) {
      if (author == null) {
         return null;
      }
      return new AuthorRedisDataModel(
              author.getId(),
              author.getName(),
              author.getAddress()
      );
   }

   public Author toAuthor(AuthorDataModel authorDataModel) {
      if (authorDataModel == null) {
         return null;
      }
      return new Author(
              authorDataModel.getId(),
              authorDataModel.getName(),
              authorDataModel.getAddress()
      );
   }

   public PublisherDataModel toPublisherDataModel(Publisher publisher) {
      if (publisher == null) {
         return null;
      }
      return new PublisherRedisDataModel(
              publisher.getId(),
              publisher.getName(),
              publisher.getAddress()
      );
   }

   public Publisher toPublisher(PublisherDataModel publisherDataModel) {
      if (publisherDataModel == null) {
         return null;
      }
      return new Publisher(
              publisherDataModel.getId(),
              publisherDataModel.getName(),
              publisherDataModel.getAddress()
      );
   }

   public BookDataModel toBookDataModel(Book book) {
      if (book == null) {
         return null;
      }
      return new BookRedisDataModel(
              book.getId(),
              book.getTitle(),
              book.getGenre(),
              book.getAuthorId(),
              book.getPublisherId()
      );
   }

   public Book toBook(BookDataModel bookDataModel) {
      if (bookDataModel == null) {
         return null;
      }
      return new Book(
              bookDataModel.getId(),
              bookDataModel.getTitle(),
              bookDataModel.getGenre(),
              bookDataModel.getAuthorId(),
              bookDataModel.getPublisherId()
      );
   }
}
```
Finally, the interface should look like this:

```java
package tutorials.databases.mappers;

import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.domain.Book;
import tutorials.databases.domain.Publisher;

public interface GeneralMapper {
   AuthorDataModel toAuthorDataModel(Author author);

   Author toAuthor(AuthorDataModel authorDataModel);

   PublisherDataModel toPublisherDataModel(Publisher publisher);

   Publisher toPublisher(PublisherDataModel publisherDataModel);

   BookDataModel toBookDataModel(Book book);

   Book toBook(BookDataModel bookDataModel);
}
```

Finally, the service layer will have to be updated to use the mappers, and the repositories will have to be updated to use
the data models:

```java
package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.repositories.datamodels.author.AuthorDataModel;
import tutorials.databases.domain.Author;
import tutorials.databases.mappers.GeneralMapper;
import tutorials.databases.repositories.AuthorRepository;
import tutorials.databases.repositories.BookRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;
   private final GeneralMapper generalMapper;

   public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, GeneralMapper generalMapper) {
      this.authorRepository = authorRepository;
      this.bookRepository = bookRepository;
      this.generalMapper = generalMapper;
   }

   public Author addAuthor(String name, String address) {
      Author author = Author.builder()
              .id(UUID.randomUUID().toString())
              .name(name)
              .address(address)
              .build();

      AuthorDataModel authorDataModel = generalMapper.toAuthorDataModel(author);
      AuthorDataModel savedAuthorDataModel = authorRepository.save(authorDataModel);
      return generalMapper.toAuthor(savedAuthorDataModel);
   }

   public Optional<Author> findById(String authorId) {
      Optional<AuthorDataModel> authorDataModel = authorRepository.findById(authorId);
      return authorDataModel.map(generalMapper::toAuthor);
   }

   @Transactional
   public void deleteAuthor(String id) {
      bookRepository.deleteAllByAuthorId(id);
      authorRepository.deleteById(id);
   }
}
```

```java
package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.repositories.datamodels.publisher.BookDataModel;
import tutorials.databases.domain.Book;
import tutorials.databases.mappers.GeneralMapper;
import tutorials.databases.repositories.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

   private final BookRepository bookRepository;
   private final GeneralMapper generalMapper;

   public BookService(BookRepository bookRepository, GeneralMapper generalMapper) {
      this.bookRepository = bookRepository;
      this.generalMapper = generalMapper;
   }

   public Book addBook(String title, String authorId) {
      Book book = Book.builder()
              .id(UUID.randomUUID().toString())
              .title(title)
              .authorId(authorId)
              .build();

      BookDataModel bookDataModel = generalMapper.toBookDataModel(book);
      BookDataModel savedBookDataModel = bookRepository.save(bookDataModel);
      return generalMapper.toBook(savedBookDataModel);
   }

   public Optional<Book> findById(String id) {
      Optional<BookDataModel> bookDataModel = bookRepository.findById(id);
      return bookDataModel.map(generalMapper::toBook);
   }

   @Transactional
   public void deleteById(String id) {
      bookRepository.deleteById(id);
   }

   public Collection<Book> findBooksByAuthorId(String authorId) {
      Collection<BookDataModel> bookDataModels = bookRepository.findBooksByAuthorId(authorId);
      Collection<Book> books = new ArrayList<>();
      for (BookDataModel bookDataModel : bookDataModels) {
         books.add(generalMapper.toBook(bookDataModel));
      }
      return books;
   }

   @Transactional
   public boolean deleteByAuthorId(String authorId) {
      return bookRepository.deleteAllByAuthorId(authorId);
   }
}
```

```java
package tutorials.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorials.databases.repositories.datamodels.book.PublisherDataModel;
import tutorials.databases.domain.Publisher;
import tutorials.databases.mappers.GeneralMapper;
import tutorials.databases.repositories.PublisherRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {

   private final PublisherRepository publisherRepository;
   private final GeneralMapper generalMapper;

   public PublisherService(PublisherRepository publisherRepository, GeneralMapper generalMapper) {
      this.publisherRepository = publisherRepository;
      this.generalMapper = generalMapper;
   }

   // Adds a new publisher with the provided name and address
   public Publisher addPublisher(String name, String address) {
      Publisher publisher = Publisher.builder()
              .id(UUID.randomUUID().toString())
              .name(name)
              .address(address)
              .build();

      PublisherDataModel publisherDataModel = generalMapper.toPublisherDataModel(publisher);
      PublisherDataModel savedPublisherDataModel = publisherRepository.save(publisherDataModel);
      return generalMapper.toPublisher(savedPublisherDataModel);
   }

   // Finds a publisher by its ID
   public Optional<Publisher> findById(String id) {
      Optional<PublisherDataModel> publisherDataModel = publisherRepository.findById(id);
      return publisherDataModel.map(generalMapper::toPublisher);
   }

   // Deletes a publisher by its ID
   @Transactional
   public void deletePublisher(String id) {
      publisherRepository.deleteById(id);
   }
}
```
# Hibernate and interfaces
We hit a new snag as we try to run the application. I tried testing with JPA repository and the application fails to start
as it cannot create the bean. The error message is as follows:
```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'authorRepository' defined in
tutorials.databases.repositories.AuthorRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.
EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException:
Not a managed type: interface tutorials.databases.repositories.datamodels.author.AuthorDataModel
```
The error message is quite clear, it cannot create an AuthorRepository bean, because it is unable to create and manage
all its building blocks. This is a problem because we are using interfaces as data models, and Hibernate is unable to 
manage interfaces, only concrete classes. A solution for this would be to change the interface AuthorDataModel to the 
concrete AuthorJPADataModel, but this would defeat the objective of having a general data model interface.

Thinking a bit about this problem, I realized that we could indeed switch the interfaces for the concrete classes at
the specific repository level. For this to work with the general repositories I had to apply some generic goodness.
This shows the changes with the example of JPAAuthorRepository:
```java  
public interface JpaAuthorRepository extends JpaRepository<AuthorJPADataModel, String>, AuthorRepository<AuthorJPADataModel> {
}
```
```java
public interface AuthorRepository<T> {
    T save(T author);
    Optional<T> findById(String id);
    void deleteById(String id);
}
```
```java
 public Author addAuthor(String name, String address) {
        Author author = Author.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();

        AuthorDataModel authorDataModel = generalMapper.toAuthorDataModel(author);
        AuthorDataModel savedAuthorDataModel = (AuthorDataModel) authorRepository.save(authorDataModel);
        return generalMapper.toAuthor(savedAuthorDataModel);
    }
```
As you can see, within the JPAAuthorRepository we specified that it used the AuthorJPADataModel, and we also specified
that the AuthorRepository interface would use the same data model. Obviously, the AuthorRepository interface had to be
updated to use generics, otherwise it would be tied to JPA. 

Finally, the service layer had to be updated to cast the data model to the correct type. This is a bit of a hassle (and
probably a bit risky?). But we'll keep it FOR SCIENCE!

## Testing databases again...
Now that we have our data models and mappers in place, we can test the application with different databases. I attempted
to use postman. Utilizing the same endpoints as before (-localhost:8080/authors?name=John&address=123 Main St.) I stopped
having bean problems with JPA and Mongo. I even went to the extent of manually connecting to each database via their shell
to check if the info was going in, which it did.

So we know both JPA repos and Mongo are working correctly, connecting to the database, and transferring info. So I attempted 
to connect Redis (I am still recovering from the Cassandra incident). 

I also reviewed the configuration files for each database, considering we are using profiles, I thought perhaps it was not
necessary to keep them. It turns out I no longer have to block the whole autoconfiguration (aside from JBDC when not using
JPA). So I have changed the application properties accordingly and removed the configuration files.

## Back to Redis
Redis is getting a bit annoying. I have followed the same principle as with other databases, ensured correct bean creation
using the configuration but can't resolve a connection. This is probably an issue with the Lettuce driver (lettuce...really?).
After considerable time testing the connections I've gathered that RedisDB accepts connection, can ping and be pinged,
but for some godforsaken reason it doesn't allow connections when the application is running. 

As life's too short and this is probably an obscure issue, I will move on to Cassandra. I believe I am starting to get
diminishing returns on this issue.

## Cassandra's second date
Interestingly enough, the data models sorted all of Cassandra's problems. I followed the same architectural principles
as before and removed the old config for Cassandra.

I tested the connection with postman with great success. Not forgetting of course that, as before, I had to define the 
namespace and the tables before sending data via postman. With this done I was able to connect to Cassandra and send
data to it.

The application properties:
```properties
spring.profiles.active=cassandra
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

spring.cassandra.contact-points=localhost
spring.cassandra.port=9042
spring.cassandra.local-datacenter=datacenter1
spring.cassandra.keyspace-name=keyspace1
spring.cassandra.schema-action=CREATE_IF_NOT_EXISTS
```

## Finishing touches
I entered a few more authors, books, and publishers into the databases to ensure everything was working correctly. The
Books queries need a bit refining, but the application is overall working as intended.

I have also added a few more endpoints to the application, such as getting all authors, books, and publishers, and deleting
authors, books, and publishers by their IDs. I have also added endpoints to get books by author ID and delete books by author ID.

This might be something I will try to pursue at a later stage, perhaps improving the communication from the controllers
(including error handling and proper status code). As it stands, the application has (mostly) succeeded in reaching the overall
goal of integrating multiple databases with Spring Boot. Obviously there is an infinite number of improvements that could be 
made, but for now, I am happy with the results. More time spent on this is having diminished returns.

I am more worried about synthesizing the information I have gathered and ensuring I have a good understanding of the concepts
I have been working with.

## Project reflection
I completed a project to deepen my understanding of different types of databases and how to interact with them using Java, 
despite having minimal experience with SQL databases and Spring Data JPA. The project aimed to integrate various databases 
into a single application using Spring Boot, covering relational databases (RDBMS) and NoSQL databases. I explored foundational 
concepts such as ACID and BASE principles, normalization, the CAP theorem, and advanced topics like query optimization, 
indexes, sharding, and replication.

In the theoretical overview, I covered the ACID principles—Atomicity, Consistency, Isolation, and Durability—and BASE 
principles—Basically Available, Soft state, and Eventual consistency—highlighting their relevance to different applications. 
I also examined the CAP Theorem, which outlines the trade-offs between Consistency, Availability, and Partition Tolerance 
in distributed systems. I explored normalization and denormalization for data organization and performance optimization, 
and discussed indexes and query optimization to enhance query efficiency. Sharding and partitioning were included to 
manage large datasets, along with database operations, fault tolerance, and security to ensure reliable and secure data 
management.

Practically, I used MySQL, PostgreSQL, MongoDB, Cassandra, Redis, and H2 to manage different types of data. Each database 
was set up independently with Spring Boot, Spring Data, JPA, Hibernate, JDBC, and the necessary drivers. The objective was 
to compare their performance and suitability for various tasks, despite the project's limited scope. I observed how each 
database type performed in real-world scenarios, gaining insights into their strengths and limitations. Relational databases 
like MySQL and PostgreSQL provided structured data management with strong consistency (and the best experience in plugging to
the app), while NoSQL databases such as MongoDB, Cassandra, and Redis offer flexibility and scalability for different use 
cases. Interacting with these databases in Java involved using JDBC for RDBMS and specific drivers for NoSQL databases. 
The repository pattern, often implemented with ORM frameworks and JPA, simplified data access, and Spring Data facilitated 
consistent data management across various databases. This project allowed me to evaluate the tools available for efficient 
database interactions in Java applications and understand the trade-offs involved in choosing different database types.

In terms of DevOps, I created a Docker Compose file to launch the databases and a common network. This setup allowed me 
to run MySQL, PostgreSQL, MongoDB, Cassandra, Redis, and H2 within their respective containers, all connected via a shared 
network. I was able to access each container and test the databases directly to ensure they were functioning correctly. 
However, I encountered issues with connectivity between Redis and the application, which I was unable to resolve during 
the project. Despite this challenge, the Docker Compose setup provided a robust environment for managing and testing the 
databases efficiently.

In terms of architecture, the project was structured with a clear separation of concerns and modularity. Each entity had 
its own controller, and each controller was paired with a dedicated service. Each service was injected with a repository 
interface, which was implemented by specific repositories tailored for each database. The main repository utilized generic 
typing to accommodate dynamic data models, while each concrete repository implementation used its own concrete data models.

To map domain entities to data models, I employed mappers managed dynamically by Spring Boot. For inversion of control, 
all database-specific structures were annotated with profiles to prevent the creation of duplicate beans, which could 
interfere with the repository, service, or controller instantiation. Additionally, significant configuration adjustments
were made in application.properties to fine-tune Spring Boot's auto-configuration. This included excluding automatic 
configuration of Cassandra-related beans based solely on the dependency in the pom.xml, and disabling JDBC's automatic 
data source configuration even for non-JPA repositories, likely due to unintended scans triggered by the dependency 
management.

Initially, the project aimed to use a single domain model with various types of annotations to accommodate different 
database requirements. However, this approach led to several issues. Spring Boot's automatic scanning of annotations 
resulted in the unintended creation of multiple beans, which caused conflicts during dependency injection. To resolve 
these problems, the project shifted to using distinct data models for each database type. This change effectively addressed 
the issues with bean creation and injection, allowing for more stable and predictable behavior within the application.
There was also an attempt to completely block the auto-configuration of all database related beans, which led to the creation 
of different configuration classes that would dynamically create database specific beans when their profile was active. 
This was later removed as it was unnecessary when the project transitioned to datamodels. However, it wasn't a complete 
waste as it helped in understanding how Spring Boot autoconfigures beans.

In terms of the use cases and datasets, the project focused on basic CRUD operations for authors, books, and publishers.
Most use cases worked, but there were some limitations in the book-related queries, which could be improved with additional
attention to their implementation. The controllers can also be improved in terms of error handling and overall communication
to the outside. 

The differences between databases in terms of performance were not able to be ascertained due to the scope of the project
and the dataset size. Nonetheless, the experience of working with different databases and plugging them into the application
helped get a semblance of how they work and that was deemed sufficient for now.

I will end this project here, as I need to focus on other projects and learning new concepts. I will potentially revisit 
this project at a later stage to utilize new gained knowledge (hopefully in springboot). It will probably be an interesting
one to come back to as I will have a better understanding of the concepts and how to implement them.
