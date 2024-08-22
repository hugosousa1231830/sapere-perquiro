# Foreword
In today’s complex systems, especially those handling thousands of user interactions and extensive data storage, 
maintaining atomic interactions presents significant challenges. Ensuring that all operations adhere to ACID principles 
(Atomicity, Consistency, Isolation, Durability) can require immense infrastructure, which may adversely impact user 
experience through increased delays and lag. This is akin to creating a traffic jam of interactions where every service 
must wait for others to complete, resulting in a slower, less responsive system.

In such scenarios, different parts of the system may experience varying levels of load. For instance, certain components 
might be accessed more frequently than others, necessitating a more robust infrastructure to handle these demands. In a 
traditional microservice architecture, this often translates to deploying multiple servers to manage high-traffic 
components effectively. However, it’s crucial to distinguish between operations that need immediate attention and those 
that can tolerate some delay. This is where Event-Driven Architecture (EDA) becomes invaluable.

EDA is designed to address these challenges by providing a framework that facilitates communication within a system while
minimizing direct dependencies between services. Rather than having services communicate directly with one another, EDA 
relies on events to signal changes or trigger actions. This decoupling reduces the tight interdependencies that can lead 
to bottlenecks and makes the system more adaptable and resilient.

By employing EDA, you can prioritize operations that require immediate processing while allowing less urgent tasks to be 
handled asynchronously. This approach not only helps in managing system load more effectively but also enhances overall 
performance and user experience. For example, in a retail application, immediate stock updates might be critical, while 
sending promotional emails can be scheduled for later. This prioritization helps maintain a smooth and responsive system.

Moreover, EDA enables more flexible scaling strategies. Components that experience high traffic can be scaled vertically 
or horizontally without affecting other parts of the system. This means you can enhance the capacity of a particular 
service or deploy additional instances as needed, ensuring that your system remains efficient and performant even under 
varying loads.

This readme was created following a bunch of tutorials and documentation. The base video is: https://www.youtube.com/watch?v=QngHCFFsa00, 
although I followed a different approach to setting up the classes as I wanted something more akin to clean architecture.
The docker setup was done following some of the instructions here: https://www.conduktor.io/kafka/how-to-start-kafka-using-docker/ as the video's ones 
were not working fully for me.

# Overview of Kafka and Event-Driven Architecture

| **Term**           | **Description**                                                                                                   |
|--------------------|-------------------------------------------------------------------------------------------------------------------|
| **Event-Driven Architecture (EDA)** | A software design pattern that allows for the production, detection, consumption, and reaction to events. For example, Class A interacts with a user and generates an event, which it sends to a Kafka broker. The broker stores the event and makes it visible only to consumers subscribed to the relevant topic, ensuring that only relevant consumers receive the events. EDA decouples subsystems by using the event broker for communication instead of direct interaction, which avoids tight coupling. |
| **Kafka**          | A distributed event streaming platform that enables the production, detection, consumption, and reaction to events. Kafka acts as the broker that stores events and makes them visible to the relevant consumers. It organizes events into **topics**, which group related events together (e.g., customer visits, orders). |
| **Broker**         | Kafka operates across one or more **brokers**. A broker is a Kafka server that receives events (messages) from producers, stores them, and serves them to consumers. Each Kafka cluster consists of multiple brokers to distribute load and provide fault tolerance. |
| **Zookeeper**      | A distributed coordination service traditionally used by Kafka to manage its brokers. Zookeeper ensures that brokers are consistently configured, manages leader election for partitions, and handles other coordination tasks. Newer versions of Kafka are starting to incorporate their own internal mechanisms for these tasks, reducing reliance on Zookeeper. |
| **Topics and Partitions** | **Topics**: Events in Kafka are organized into topics, which serve as categories or feeds of messages. <br> **Partitions**: Each topic is divided into partitions, which are crucial for Kafka's scalability. Partitions allow Kafka to distribute data across multiple brokers, enabling parallel processing and high throughput. |
| **Producer**       | A producer is a client application that creates and sends events to a Kafka broker. Producers publish events to one or more topics. The broker then stores the events in the relevant topic(s). |
| **Consumer**       | A consumer is a client application that reads and processes events from a Kafka broker. Consumers subscribe to one or more topics and handle the incoming events as they are received. |
| **Listeners**      | A listener is a method or function that reacts to changes in state or events. In Kafka, a listener is typically part of a consumer that handles events from a topic. The listener is triggered whenever an event is published to that topic. Kafka generally operates in a polling loop, repeatedly "asking" the broker for new messages using the `poll()` method. If the `poll()` method returns messages, the listener is triggered to process them. This polling mechanism is abstracted away in frameworks like Spring Boot, where the `@KafkaListener` annotation manages polling and listener invocation. |

# Setting up a Spring Boot Project with Kafka
To create a Spring Boot project that interacts with Kafka, we need to set up the following components:
1. We can use Spring Initializr to create a new project. I am using Maven, packing in JAR and JAVA 17. As dependencies I 
am adding Lombok and Kafka (the plain one, not the Streams's one). 
2. Following the tutorial, there is a point where it asks for the Jackson library to be added to the POM, might as well 
add it now:
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```

# Setting Up Kafka in docker
Starting with a little bit of DevOps, we will be setting up Kafka and creating a simple producer-consumer application.
There are a number of ways of setting up kafka, but for the sake of simplicity we will be using docker to set it up.

1. Ensure docker desktop is installed.
2. Create a new file and name it "zk-single-kafka-single.yml" (I downloaded with this name, you may pick another name). 
It is a very standard docker compose file which starts a kafka and zookeeper (1 instance of each). Important note: The 
port that we will be using to connect to kafka is 9092, this is one of the lines in the docker compose file. The code is 
as follows:
```yaml
version: '2.1'

services:
  zoo1:
    image: confluentinc/cp-zookeeper:7.3.2
    hostname: zoo1
    container_name: zoo1
    ports:
      - "2181:2181"
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_SERVER_ID: 1
      ZOOKEEPER_SERVERS: zoo1:2888:3888

  kafka1:
    image: confluentinc/cp-kafka:7.3.2
    hostname: broker
    container_name: broker
    ports:
      - "9092:9092"
      - "29092:29092"
      - "9999:9999"
    environment:
      KAFKA_ADVERTISED_LISTENERS: INTERNAL://kafka1:19092,EXTERNAL://${DOCKER_HOST_IP:-127.0.0.1}:9092,DOCKER://host.docker.internal:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT,DOCKER:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: INTERNAL
      KAFKA_ZOOKEEPER_CONNECT: "zoo1:2181"
      KAFKA_BROKER_ID: 1
      KAFKA_LOG4J_LOGGERS: "kafka.controller=INFO,kafka.producer.async.DefaultEventHandler=INFO,state.change.logger=INFO"
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_JMX_PORT: 9999
      KAFKA_JMX_HOSTNAME: ${DOCKER_HOST_IP:-127.0.0.1}
      KAFKA_AUTHORIZER_CLASS_NAME: kafka.security.authorizer.AclAuthorizer
      KAFKA_ALLOW_EVERYONE_IF_NO_ACL_FOUND: "true"
    depends_on:
      - zoo1
```

3. Navigate to the project root and run `docker-compose -f zk-single-kafka-single.yml up -d`. The `-d` flag is used to 
run the containers in the background. If you want to see the logs, you can remove the "-". This will start both kafka and zookeeper.
4. To check they are running, run `docker ps`. You should see both kafka and zookeeper running. Kafka's aliases is "broker"
and zookeeper's alias is "zoo1". This is relevant for when we want to interact with kafka.

# Interacting with Kafka via the terminal
So keeping in mind that Kafka is being served on port 9092, we can interact with it via the terminal. The base command to
interact with docker is `docker exec`, while specifying the container we want to interact with, in this case "broker". 
As follows:
```bash
docker exec broker \
```

1. To remember, a topic is a category or feed name to which messages are sent. Kafka does not necessarily need to have a 
topic created before sending messages to it, but it is a good practice to do so (we can turn this off in the properties file).
We will start by creating a topic using the kafka-topics command (This command can be pasted as it is in the terminal):
```bash
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
--create \
--topic "customer.visit"
```
The above command does the following:
#1: The docker exec broker command is used to run a command in the broker container.
#2: The kafka-topics command is used to manage topics in Kafka.
#3: The --bootstrap-server flag is used to specify the Kafka broker we want to connect to (This explanation is a bit vague
on purpose. We could argue knowing "broker:9092" is enough, but it seems we need bootstrap-server... for devops reasons. 
Just take it).
#4: The --create flag is used to create a topic.
#5: The --topic flag is used to specify the name of the topic we want to create. In this case, we are creating a topic called "customer.visit".
There should be a confirmation message: Created topic customer.visit.

2. To check if the topic was created, we can list all the topics in the Kafka broker using the following command:
```bash
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
--list
```

3. Now let's add a message to the topic we just created. We can use the following command:
```bash
docker exec --interactive --tty broker \
kafka-console-producer --bootstrap-server broker:9092 \
--topic "customer.visit"
```
The above command does the following:
#1: The docker exec --interactive --tty broker command is used to run a command in the broker container.
#2: The kafka-console-producer command is used to produce messages to a topic.
#3: The --bootstrap-server flag is used to specify the Kafka broker we want to connect to.
#4: The --topic flag is used to specify the name of the topic we want to send messages to. In this case, we are sending 
messages to the "customer.visit" topic.
This will open a console where we can write messages to the topic. Try writing a couple of messages and pressing enter to send them.

4. To check the messages in the topic, we can use the following command:
```bash
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server broker:9092 \
--topic "customer.visit" \
--from-beginning
```
The above command does the following:
#1: The docker exec --interactive --tty broker command is used to run a command in the broker container.
#2: The kafka-console-consumer command is used to consume messages from a topic.
#3: The --bootstrap-server flag is used to specify the Kafka broker we want to connect to.
#4: The --topic flag is used to specify the name of the topic we want to consume messages from. In this case, we are 
consuming messages from the "customer.visit" topic.
#5: The --from-beginning flag is used to consume messages from the beginning of the topic. This flag is optional and can 
be omitted if you only want to consume new messages.
This will show all the messages in the topic. You should see the messages you sent earlier. Notice it leaves a command 
line open, which means if you open a new terminal and add more messages to that topic, they should appear there dynamically.


# Setting up a producer and consumer in Spring Boot

Let's start by setting up a simple scenario where a user interacts with our system via a rest endpoint. This interaction
will trigger an event that will be sent to a Kafka topic. We will then have a consumer that will listen to that topic and
consume the message, printing it to the console.

So, we will create a controller that will have a POST endpoint that, when hit, will create a random event using a
producer (which acts like a service). The producer will then send the event to the Kafka topic. In the meantime, we will
have a consumer that will listen to that topic and print the message to the console.

1. Let's start by creating a domain class that will represent the event that will be sent to Kafka. This class will have
two fields: a customer ID and a date time. To make it really straightforward, we will be using the Lombok library which abstracts 
a lot of boilerplate code using annocations. The code is as follows:
```java
package com.kafka.kafka_eda.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerVisitEvent {

    private String customerID;
    private LocalDateTime dateTime;

}
```
2. Next, we will create a json configuration class that will be used to serialize the object to a string. This is required
as Kafka only accepts strings. We could simply place this in the producer class, but it is a good practice to separate
concerns, and as we are learning how to use kafka, it is ideal to not mix up the logic.
The code is as follows:
```java
package com.kafka.kafka_eda.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        return objectMapper;
    }
}
```

3. Next, we will create the producer class. Things to keep in mind: we will be using the kafka api to send the message to
the topic (kafkaTemplate); we will be using jackson to serialize the object to a string (this is required as Kafka only
accepts strings); The code is as follows:

```java
package com.kafka.kafka_eda.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CustomerVisitEventProducer(KafkaTemplate<String, String> kafkaTemplate,
                                      ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEvent(CustomerVisitEvent event) throws JsonProcessingException {
        final String payload = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("visitor", payload);
    }
}

```
Let's break down the code:
1. The dependencies: We need to make sure we can convert the object to a string; We need to make sure we can communicate
with Kafka using the kafkaTemplate;
2. The constructor: We are injecting the kafkaTemplate and the objectMapper;
3. The sendEvent method: This method will receive an event, serialize it to a string, and send it to the Kafka topic.
To send the message, we use the kafkaTemplate.send() method, passing the topic name and the serialized event as the payload.
4. The topic name is hardcoded as "visitor". This is the topic we will be sending the events to. We will not create the topic
in the code, but we could do so in the application.properties file, in theory it should be created automatically.

4. Next, we will create a controller that will have a POST endpoint to send the event to Kafka. There is a number of 
things wrong with this controller in terms of proper arquitecture, for instance the event details could be obtained via 
@RequestBody, and then a separate service would create the event, etc. But for the sake of simplicity, we will keep it as
it is. The code is as follows:
```java
package com.kafka.kafka_eda.domain;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/visit")
public class CustomerVisitController {

    private final CustomerVisitEventProducer eventProducer;

    public CustomerVisitController(CustomerVisitEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping()
    public String sendCustomerVisitEvent() {
        try {
            CustomerVisitEvent event = CustomerVisitEvent.builder()
                    .customerID(UUID.randomUUID().toString())
                    .dateTime(LocalDateTime.now())
                    .build();
            eventProducer.sendEvent(event);
            return "I AM THE PRODUCER AND I JUST SENT A FRESH NEW EVENT!";
        } catch (Exception e) {
            return "Failed to send event: " + e.getMessage();
        }
    }
}
```
Let's break down the code:
1. We are letting springboot know that this is a controller and that it will be listening to the "/visit" endpoint.
2. We are injecting the eventProducer in the constructor.
3. We have a POST endpoint that will create a random event and send it to Kafka. The event consists of a random customer ID
and the current date and time. We then send the event using the eventProducer.sendEvent() method.
4. If the event is sent successfully, we return a success message. If an exception occurs, we return an error message.

5. Finally, we will create a consumer that will listen to the Kafka topic and consume the events. The consumer will print
the events to the console. The code is as follows:
```java
package com.kafka.kafka_eda.domain;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitEventConsumer {

    @KafkaListener(topics = "visitor")
    public void listen(String message) {
        System.out.println("I AM THE CONSUMER AND I LISTENED TO THIS: " + message);
    }
}
```
Let's break down the code:
1. We are letting springboot know that this is a service.
2. We are using the @KafkaListener annotation to specify that this method should listen to the "visitor" topic.
3. The listen() method will be triggered whenever a new message is published to the "visitor" topic. The message will be
printed to the console. 

Now we can run the application and test it. Just hit run on the main class and then go to postman, select POST, and hit
http://localhost:8080/visit. You should see the controller returning the message: "I AM THE PRODUCER AND I JUST SENT A FRESH NEW EVENT!".
Now if you hop over to the console where the application is running, you should see the message: "I AM THE CONSUMER AND I LISTENED TO THIS: {...}".
Additionaly, you can use the kafka-console-consumer command to see the messages in the topic, like we did before. If you 
press the endpoint a couple of times, you should see the messages appearing in the console.

# Conclusion
This is a very basic example of how to use Kafka in a Spring Boot application. There are a lot of things that can be
improved, such as the controller, the producer, and the consumer. For instance, the controller could be improved to
receive the event details via @RequestBody, and then a service could be used to create the event. The producer could be
improved to handle exceptions and retries, and the consumer could be improved to handle deserialization errors and
message processing errors. Additionally, we could add more topics and consumers to handle different types of events.
Overall, Kafka is a powerful tool that can be used to build scalable and reliable event-driven systems. It provides
features such as fault tolerance, scalability, and high throughput, making it ideal for handling large volumes of data
and events. By using Kafka in combination with Spring Boot, we can build robust and efficient systems that can handle
complex event processing requirements.
