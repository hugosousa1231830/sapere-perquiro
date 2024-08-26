## Info Sources
https://graphql.org/learn/best-practices/
https://www.youtube.com/watch?v=eIQh02xuVw4 - Always a good start for a 2min intro
https://www.youtube.com/watch?v=-0od_vt0s6A
https://www.youtube.com/watch?v=l-7JqCzVMJw&t=551s
https://www.youtube.com/watch?v=ZQL7tL2S0oQ&t=1330s&pp=ygUHZ3JhcGhxbA%3D%3D - Nice intro, but code in Express/Node.js

## Understanding GraphQL
GraphQL is a query language used for APIs. It differs from REST, which typically uses multiple endpoints for different 
resources. With GraphQL, there is only one endpoint. This allows the client to specify the data they want to receive in 
a single request, even if the data comes from multiple sources. 

# How does GraphQL differ from REST?
Let's imagine we have a REST API that has two resources, authors and books. Consider the objects in java:
```java
public class Author {
    private String id;
    private String name;
    private String address;
}

public class Book {
    private String id;
    private String title;
    private String authorId;
}
```
And the following endpoints:
```http
GET /authors
GET /authors/{id}/books
```
With Rest, if you were looking to get a list of the names of all authors and their respective books, you would have to make
two requests to the server, one to get the list of authors, and another to get the list of books for each author. On top
of that, the response would contain all the information about the author, even if you only wanted the name. This is where
GraphQL comes in, as it allows the client to specify the data they want to receive in a single request. 

# Understanding GraphQL Schemas
The backbone of a GraphQL API is its schema. The schema could be thought as a kind of restaurant menu, as it defines the 
types of data that can be queried and the operations that can be performed. To define a schema, you start by describing 
the types of objects. For example, if our code includes the following Java class:
```java
public class Author {
    private String id;
    private String name;
    private String address;
}
```
Then the way we would define this Post object in the schema would be as follows:
```graphql
type Author {
    id: ID!
    name: String!
    address: String
}
```
An object is defined by the keyword `type`, followed by the name of the object, and then the parameters that the object has.
Within curly braces, we first list the name of each parameter, followed by the type of the parameter and the `!` symbol means that
the parameter is required. This means that the client must provide a value for this parameter when making a request.

Now lets look at the types of operations that can be performed on the objects. There are three types of operations that can be
performed on an object, these are queries (GET), mutations (POST), and subscriptions (??). Queries are used to fetch data from 
the server, mutations are used to change data on the server, and subscriptions are used to listen for changes to the data on the server.

Considering the previous example, lets imagine we have the following methods in our controller (do not worry about the code,
just focus on the method names and try to understand what they do):
```java
public Author getAuthor(String id) {
    return authorService.getAuthor(id);
}

public Author createAuthor(Author author) {
    return authorService.createAuthor(author);
}
```
Then the way we would define these methods in the schema would be as follows:
```graphql
type Query {
    getAuthor(id: ID!): Author
}

type Mutation {
    createAuthor(author: AuthorInput!): Author
}
```
Relating the java methods to the schema, we can see that the `getAuthor` method is a query, as it is used to fetch data from the
server, it takes an id as a parameter, and returns an Author object. The `createAuthor` method is a mutation, as it is used to
change data on the server, it takes an Author object as a parameter, and returns an Author object (This is similar to how 
we present the information in a Class diagram).

# Interacting with the API
To interact with the API, the client sends a request to the server, usually in the form of HTTP POST, even if it is a query.
This is because graphQl requires a body to be sent with the request (remember the client must say what they want!), and this 
body contains the query that the client wants to perform. An example of a query that the client might send to the server is as follows:
```graphql
query {
    getAuthor(id: "1") {
        id
        name
    }
}
```
Utilizing postman, the client would send a POST request to the server, with the body containing the query above. 
An example of a postman request is as follows, first the HTTP Request:
```
POST http://localhost:8080/graphql
```
Then the body of the request would be as follows:
```json
{
    "query": "query { getAuthor(id: \"1\") { id name } }"
}
```
The server would then return the following response:
```json
{
    "data": {
        "getAuthor": {
            "id": "1",
            "name": "John Doe"
        }
    }
}
```
I will try to explain the response in a bit more detail later when practicing with the code.

## TLDR
So to summarize, in order to use GraphQl we must define a schema, describe the objects that we have, and the operations that can
be performed on them. Then GraphQl provides a single endpoint that the client can use to send requests to the server. The
client has the responsibility of specifying, on the request body, the data that they want to receive. And the server will
take that body, and return the data that the client requested.

# Practicing with GraphQL
## Setting up the project
To practice with GraphQL, we will use the Spring Boot framework. To start, we will create a new Spring Boot + Maven project 
using the Spring Initializr. We will add the following dependencies to the project:
- Spring Web
- Spring for graphql
- Lombok (alwaaaaaays)
For the purposes of configuration perhaps call the project `graphqltutorial`. This is just to avoid any confusion with the
application.properties file.

## Setting up the java code:
Let's start by creating an Author and Book objects in java as follows:
```java
package graphql.practice;

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
package graphql.practice;

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
    private String authorId;
}
```

Now we are creating a class that will serve as MEMORY repository for the Author and Book objects and we will call it Service.
This is obviously wrong, as we are mixing the repository and service layers, but for the sake of simplicity we will do it.
Keep in mind, all we want is to work on the interaction between the client and the server, so we are not focusing on the
architecture of the project. Ideally we would have controller -> service -> repository -> database.
The code for the service class is as follows:

```java
package graphql.graphqltutorial;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Map<String, Book> books = new HashMap<>();

    public Book createBook(String title, String authorId) {
        Book newBook = Book.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .authorId(authorId)
                .build();

        books.put(newBook.getId(), newBook);

        return newBook;
    }

    public Collection<Book> findByAuthor(String authorId) {
        return books.values().stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
}
```
Note: The createBook method uses the Lombok builder along with the setters to gradually create the object, and once "finished"
it does the build() method to create the object to finalize it (it is great to create these simple objects but on an IRL 
scenario there is a lot to be said about the atomicity of the object creation and object integrity safeguards).
. It also used UUID to generate a random id for the book.

```java
package graphql.graphqltutorial;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorService {
    Map<String, Author> authors = new HashMap<>();

    public Author createAuthor(String name, String address) {
        Author newAuthor = Author.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .address(address)
                .build();
        authors.put(newAuthor.getId(), newAuthor);
        return newAuthor;
    }
    
    Author findById(String id) {
        return authors.get(id);
    }
}
```

Now we will create a controller class that will be responsible for handling the requests from the client. We will call it
libraryController. Let's create the class first, then go through all the different features:

```java
package graphql.graphqltutorial;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class LibraryController {
    private final AuthorService authorService;
    private final BookService bookService;

    public LibraryController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @QueryMapping
    public Author authorById(@Argument String id) {
        return authorService.findById(id);
    }

    @MutationMapping
    public Author createAuthor(@Argument String name, @Argument String address) {
        return authorService.createAuthor(name, address);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument String authorId) {
        return bookService.createBook(title, authorId);
    }

    @SchemaMapping
    public Collection<Book> books(Author author) {
        return bookService.findByAuthor(author.getId());
    }
}
```

The first thing to notice, other than the `@Controller` annotation are all the graphql annotations. These annotations are used
to tell graphql that the methods are to be mapped to the graphql schema. As a side note, these methods are often called "resolvers",
as they resolve the queries and mutations that the client sends to the server.

The `@QueryMapping` annotation is used to map a method to a GraphQL query field (If it was Rest, it would be a GET request 
with @GetMapping). The `@MutationMapping` annotation is used to map a method to a GraphQL mutation field (If it was Rest, 
it would be a POST or PUT request with @PostMapping). The `@SchemaMapping` annotation is a bit different, as it is used to 
"stitch" data from different sources. In this case, it is used to get all the books from an author. So it is mixing data 
from the AuthorService and the BookService. Finally, the `@Argument`annotation is used to specify the parameters that the 
method will need to receive (If it was Rest, it would be the @PathVariable or @RequestBody annotations).

## Setting up the schema
Now that we have the java code set up, we need to define the schema. Normally the schema can be spread across multiple files,
separated by "concern" and followed by .graphql extension. For this example, we will define the schema in a single file. The schema
for this example is as follows:
```graphql
type Author {
    id: ID!
    name: String!
    address: String
}

type Book {
    id: ID!
    title: String!
    authorId: String!
}

type Query {
    authorById(id: ID!): Author
    books(authorId: ID!): [Book]
}

type Mutation {
    createAuthor(name: String!, address: String): Author
    createBook(title: String!, authorId: String!): Book
}
```

So as a refresher, the schema defines two objects, Author and Book. Each object has fields (their parameters), the fields
have a type and can be required or not (if they cannot be null, then they must be followed by the `!` symbol).

Now the schema also defines 3 operations that can be performed on the objects. The `Query` type shows the `authorById` method,
and refers that it takes an id as a parameter and returns an `Author` object (notice that the `!` symbol is used to indicate
that the object cannot be null). The `Mutation` type shows the `createAuthor` and `createBook` methods. Again, the methods take
parameters and return objects.

There is one last resolver that we need to define, and that is the `books` resolver. Let's add [Book] to the Author object in the
schema. Don't create a new entry, just put books in the Author object:
```graphql
type Author {
    id: ID!
    name: String!
    address: String
    books: [Book]
}
```
This can be slightly confusing. Let's start by defining what is a parent and what is a child in the context of graphQl.
The parent is the object that is being queried, and the child is the object that is being resolved. In this case, the parent
is the Author object, and the child is the Book object. When querying the author, graphql utilizes the query to obtain the
author object, and then the `books` resolver to obtain the required details of the child object. This is why the `books`
resolver is defined in the Author object in the schema.

In a more ludicrous example, lets say we have a new object call "Page" that has a parent object of "Book". If we wanted to
get the pages of a book, we would define the `pages` resolver in the Book object in the schema. This is because the Book
object is the parent object, and the Page object is the child object. It would be something like this (no need to copy this
bit):
```graphql
type Book {
    id: ID!
    title: String!
    authorId: String!
    pages: [Page]
}
```

Going back to the author and book example. Notice that the field `books` in the Author object is defined as a list of Book
objects. This is because an author can have multiple books. The `books` resolver in the LibraryController class is used to
get all the books from an author. The resolver takes an Author object as a parameter, and returns a list of Book objects.
Make sure to name the resolver method the same as the field in the schema.

Note: On method/resolver names and annotations: We have been very happily using the @QueryMapping, @MutationMapping, and
@SchemaMapping annotations without any issues. However, this is because we are ensuring the names are similar. But let's say
we wanted to use a different name for the resolver method, we would have to use the `name` attribute in the annotation. For
example, if we wanted to use a different name for the `authorById` resolver, we would do the following:
```java
@QueryMapping(name = "authorById")
public Author getAuthor(@Argument String id) {
    return authorService.findById(id);
}
```
Nonetheless, this just makes the code more confusing, so it is better to keep the names the same. 

## Running the application and using GraphiQL
Let's make sure our application.properties file is set up to use GraphiQL. This should already be correct if you named 
the project `graphqltutorial`:
```properties
spring.application.name=graphqltutorial
```

To interact with a GraphQL API, we will use GraphiQL which is a tool that provides a user-friendly interface that allows 
you to write queries and mutations, and see the results in real-time. Its similar to Postman, but for GraphQL. 
To use GraphiQL we simply need to add the following line to the application.properties file:
```properties
spring.graphql.graphiql.enabled=true
```
Spring Boot will automatically create a GraphiQL interface at the `/graphiql` endpoint. To access the interface, we can
navigate to `http://localhost:8080/graphiql` in the browser.
But first we need to run the application. To do this, we can run the following command in the terminal:
```
mvn spring-boot:run
```
Once the application is running, we can navigate to `http://localhost:8080/graphiql` in the browser. We should see the
GraphiQL interface, which will allow us to write queries and mutations and see the results in real-time.

## Diving into the layout of GraphiQL
On the left hand side let's start by going unto the "Explorer" tab (third from top-left). This will probably have a 
default query setup. But basically you can interact with this tab to see the different queries and mutations that are
available. Let's start by adding a few new mutations by pressing "Add New Mutation" and pressing "+". Notice how on the
right hand side the query is being built. This is the query that will be sent to the server. We may erase the default 
query that is there from start (if it appears) in order to create a new Author and Book.

You may interact with the drop-down menus to see the different queries and mutations that are available. Or simply paste
the following code unto the middle of the screen to create 2 new authors. Also please note that the id of the author is
being returned, so we can use this id to create a book for the author. The returns will be displayed on the right:
```graphql
mutation createAuthor1 {
    createAuthor(name: "Stig Dagerman") {
        id
        name
        address
    }
} # ID AUTHOR 1 = ____________________________________

mutation createAuthor2 {
    createAuthor(name: "Stephen Hawking", address: "23 Grove Street") {
        id
        name
        address
    }
} # ID AUTHOR 2 = ____________________________________
```
Lets create a few books for the authors. Using the previously saved IDs, copy the following code into the left hand side:
```graphql
mutation createBook1 {
    createBook(title: "A moth to a flame", authorId: "ID AUTHOR 1") {
        id
        title
        authorId
    }
}

mutation createBook2 {
    createBook(title: "Brief Answers To The Big Questions", authorId: "ID AUTHOR 2") {
        id
        title
        authorId
    }
}

mutation createBook3 {
    createBook(title: "The Grand Design", authorId: "ID AUTHOR 2") {
        id
        title
        authorId
    }
}
```
Perfect so now we have some data in the system. Let's try to get the data back. We will start by getting all an author's
name, address and id, by providing the author's id. Copy the following query into the right hand side:
```graphql
query getAuthorById {
    authorById(id: "d56ecebb-26fc-4997-b07a-7d915acbe394") {
        address
        name
        id
    }
}
```
Now try removing the name and id from the request, and see how the response changes:
```graphql
query getAuthorById {
    authorById(id: "d56ecebb-26fc-4997-b07a-7d915acbe394") {
        address
    }
}
```
Our frontend days (more like minutes) of looping through an infinite amount of JSON returns to find the data we want
are over. 

Finally lets get the name of the author and the titles of his books. Copy the following query into the right hand side:
```graphql
query getBooksByAuthor {
    authorById(id: "d56ecebb-26fc-4997-b07a-7d915acbe394") {
        name 
        books {
            title
        }
    }
}
```
And here we have it, even though the books are stored in a different service, we only had to call one endpoint to get all the
information we needed.

## Finishing up
Using graphQL can be a bit tricky to understand as it differs from REST, however when looking at it from a high level, it
is quite simple. The client sends a request to the server, specifying the data they want to receive. The server then uses
the schema to determine how to resolve the request, and returns the data to the client. This is a very high level overview
of how GraphQL works, and there is a lot more to learn. But this should be a good starting point to get you up and running
with GraphQL.

When researching for this tutorial, I found a lot of good resources that I linked at the beginning of this document. And
there is definitely a lot more to learn. 

I believe that to use GraphQL in a real project, one would need to learn a lot more about the different features that 
GraphQL offers but to try and master everything without touching a live project might be counterproductive, and possibly
a pure waste of time at this stage. 

While creating this tutorial I kept asking myself a few questions. These may serve as a good starting point for further study:
- What kind of architecture should we use when building a GraphQL API? How do we structure the code? How do we ensure that
  the code is maintainable and scalable? Do we start by defining the schema or do we start by defining the resolvers?
- How do we handle complex queries in a GraphQL API (unions, subqueries, yikes - Maybe the databases still can do this)? 
- How do we handle errors in a GraphQL API? How do we ensure that the client receives the correct error messages? What about
  status codes (like HTTP status codes), is there any universal status code for GraphQL?
- How do we handle authentication and authorization in a GraphQL API? How do we ensure that the client is authorized to 
  access the data?
- How do we handle subscriptions in a GraphQL API? How do we ensure that the client receives real-time updates to the data?
  (I might have to come back to this one... As I believe I'll be doing this a LOOOOT in the future)
- What are concepts like fragments, directives, introspection, pagination, filtering and batching?