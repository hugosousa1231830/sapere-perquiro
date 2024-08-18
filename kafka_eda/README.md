TERMS: Topics, consumer, producer, POJO, broker, zoo

So this project will be a medium to understand a bit more about kafka and eda. Ill be setting up a project using spring initializr
so i dont have to worry about the devops aspect of it. Ill include kafka and lombok as dependencies.

First of all we need to start kafka, we can use docker:
1. I already have docker desktop installed.
2. Follow the instructions here: https://www.conduktor.io/kafka/how-to-start-kafka-using-docker/
3. TLDR I copied the file "zk-single-kafka-single.yml" which is a very standard docker compose file which starts a kafka and zookeeper (1 instance of both. While kafka is the event streaming lpatform we will be using, zookeeper is a way of ensuring all kafka brokers work together well. Zookeeper is not going to be very used, but for the purposes of this it will work.)
4. To start both navigate to the project root and run `docker-compose -f zk-single-kafka-single.yml up`;
5. Keep in mind that the port that we will be using to connect to kafka is 9092.

We will try to communicate with kafka via the docker to create a topic. Simply paste this code:
```bash
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
--create \
--topic "customer.visit"
```
When using docker exec broker we are running a command in the broker container. 
The command we are running is kafka-topics, which is a kafka command to manage topics. 
The --bootstrap-server flag is used to specify the kafka broker we want to connect to. 
The --create flag is used to create a topic. 
The --topic flag is used to specify the name of the topic we want to create.
The above code will create a topic called "customer.visit" in the kafka broker. We can check if the topic was created by running the following command:
```bash
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
--list
```

Now do add a message to the topic we just created, we can use the following command:
```bash
docker exec --interactive --tty broker \
   kafka-console-producer --bootstrap-server broker:9092 \
                          --topic "customer.visit"
```
This will open a console where we can write messages to the topic. We can write a message and press enter to send it.
To check the messages in the topic we can use the following command:
```bash
docker exec --interactive --tty broker \
   kafka-console-consumer --bootstrap-server broker:9092 \
                          --topic "customer.visit" \
                          --from-beginning
```
This will show all the messages in the topic. We can see the message we sent earlier.

Obviously, we want to setup something more automated. We will be using spring kafka to do that.
We will be creating a producer and a consumer. The producer will send messages to the topic and the consumer will read the messages from the topic.
To create a producer, we must identify what kind of messages we will be sending. We will be sending a POJO (Plain Old Java Object) converted into JSON, using the Jackson library.

We need to make sure the depdency for jackson is on the POM file. 

We start by creating the CustomerVisitEvent class. This class will be the POJO we will be sending to the topic. Keep in 
mind its important to have the no args constructor as it is used by the jackson library to convert the object to json.
```java
package domain;

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

In order to use Jackson, we could create a new class and use the ObjectMapper class to convert the object to json. However, we will create a JsonConfig class:
```java
@Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        return objectMapper;
    }
```
The above code does the following: #1: Creates a new ObjectMapper object. #2: Registers the JavaTimeModule to handle the 
conversion of Java 8 Date and Time API classes. #3: Sets the date format to be used when converting the date to a string.
It is all basically telling Jackson in what way we want the Json file to be formatted in.

Now that we have an object that can deal with json converting, we can create the producer, which will send the messages to the topic.
To create a class that serves as a producer, this class must be able to utilize the kafkaTemplate. 
The kafkaTemplate is a class that allows us to send messages to a topic. 
We will be using the KafkaTemplate<String, String> class, which is a class that sends messages with a key and a value, 
both as strings. I wont go in as to why we have String, String as the types, but it is a good practice to have the key and value as strings.

The following code will create a producer that sends a message:
```java
@Bean
public ApplicationRunner runner (KafkaTemplate<String, String> kafkaTemplate, KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
final CustomerVisitEvent event = CustomerVisitEvent.builder()
.customerID(UUID.randomUUID().toString())
.dateTime(LocalDateTime.now())
.build();
final String payload = objectMapper.writeValueAsString(event);
return args -> {
    kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
    };
}
```
The above code does the following: 
#1: Creates a new CustomerVisitEvent object using the builder with a random ID and the current time;
#2: Converts the object to a JSON string with the objectMapper we previously defined;
#3: Utilizes the kafkaTemplate to send the message to the topic using the send method, which receives the topic name and 
the message (it gets teh topic name via a class we setup and placed on application.properties. This will need to be revisited later);
#4: The return statement is a lambda expression. This expression is required by the ApplicationRunner interface, which is a
Spring Boot interface that allows us to run code when the application starts. The lambda expression will send the message to the topic when the application starts.

Ok, to test it all out we run the application and check the topic for the message. We can use the following command to check the messages in the topic:
```bash
docker exec --interactive --tty broker \
   kafka-console-consumer --bootstrap-server broker:9092 \
                          --topic "customer.visit" \
                          --from-beginning
```

So if we have a terminal with the docker command above running, we should see the message we sent. And every time we run 
the application we should see a new message, with a new ID and date time.

You may also try swapping out the kafkaConfigProps.getTopic() for "customer.visit". It works the same.

Great. So now lets try creating a consumer. We can use the main class to do that. We will be using the @KafkaListener annotation to listen to the topic and consume the messages.
```java
@KafkaListener(topics = "customer.visit")
	public String listen(final String in) {
		System.out.println(in);
		return in;
	}
```
The above code does the following:
#1: The @KafkaListener annotation is used to listen to the topic "customer.visit". This annotation is used to create a consumer that listens to a topic.
#2: The listen method is called when a message is received. The method receives the message as a string and prints it to the console.
#3: The method returns the message. This is not necessary, but it is useful to return the message in case we want to do something with it.

Before we can make this work though we must create configuration for the consumer on the application.properties file. 
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=events
```
The above code does the following:
#1: The spring.kafka.bootstrap-servers property is used to specify the Kafka broker we want to connect to. In this case, we are connecting to localhost:9092.
#2: The spring.kafka.consumer.group-id property is used to specify the consumer group ID. The consumer group ID is used to identify the consumer group. In this case, we are using the group ID "events" (this needs to be revisited).

So when the application starts, the producer will send a message to the topic and the consumer will consume the message and print it to the console.

