## On elastic search
Elastic search is a distributed, RESTful search and analytics engine capable of solving a growing number of use cases. 
It is part of the elastic stack, and its main use cases is to store, search and analyze large volumes of data quickly 
and in near real-time. It is known for its speed, scalability, and ease of use. It is built on top of Apache Lucene,
a full-text search engine library written in Java.

Elastic search comes in handy in situations such as when conducting a search on a website, we want the information to be 
as fast as possible but also to be relevant to the user and accurate.

Typically, the flow of operations in elastic search is as follows:
1. The client sends a request to a server
2. The server processes the request and sends it to the elastic search engine
3. The elastic search engine processes the request and sends it back to the server
4. The server sends the response back to the client

When elastic search is running, you have an instance of elastic search known as a Node. Each node has an unique ID and
name and belongs to a cluster. A cluster is formed automatically when the first node starts up. Each node can be running
from different machines or on the same machine, but nonetheless are can be part of the same cluster and work together
to accomplish the same goal. Nodes can be assigned roles such as master, data, or ingest nodes (basically each node
specializes in a particular task). For example, master nodes are responsible, within other things, of knowing which nodes
hold information about which data.

Data is stored in elastic search in the form of documents. A document is a JSON object (key-value pairs) that has a unique 
ID and is stored in an index. An index is a collection of documents that have similar characteristics. For example, if we
have a collection of books, we can store them in an index called "books". 

Important note: Even though theoretically we say we are storing documents in an index, in reality, index is not a "palpable"
object, it is a logical partition. For example, we wouldn't be able to search for an index in the disk, like we 
would for an ID. For that we use the concept of shards.

Shards are where the data is actually stored, and this is where the search and retrieval operations are performed.

When we create an index, we get a shard by default, but we can also specify the number of shards we want. This practice
is called "Sharding". Sharding is important because it allows us to distribute the data across multiple nodes, which
increases the performance of the system. The amount of data a shard can hold, is determined by the amount of the capacity 
of the node it is stored on. So the concept of sharding is ideal for horizontal scaling. For example if we have 500.000
documents to store about books, and each shard holds 100.000 documents, we would need 5 shards to store all the data. And
we if we need to add more, we just add more shards.

To further illustrate the use of shards, let's consider an example of a client that wants to find a book. Now if we have
a single shard that holds 500.000 books, the search operation would happen sequentially on a single shard, which would
take ages. Now if we have 5 shards with 100.000 books each, the search operation would happen in parallel on all 5 shards,
which would be much faster.

In terms of fault tolerance, elastic search has a feature called "replicas". Replicas are copies of the shards and hold 
a backup of the data, providing fault tolerance in case the original fails. They also can help in distributing the search
load. For example, if we have 1 shard and 1 replica, that specific data is stored in 2 nodes, so a particular search
can be done in parallel on both nodes.

Before proceeding to the practical side its important to mention disadavantages of elastic search. Elastic search is limited
in terms of language support, and it is not the best choice for complex queries. Also there is risk of split-brain 
situation, where the cluster is divided into two or more sub-clusters, which can lead to data loss. Split-brain is 
when the master node loses connection with the other nodes, and the other nodes elect a new master. When the original
master regains connection, it thinks it is still the master, and the other nodes think they have a new master, which
leads to data inconsistency.

## Setting up ElasticSearch using docker compose
We can start by serving ElasticSearch in a docker container. We can use a docker-compose.yml file to do this. Here is an
example of a docker-compose file that serves ElasticSearch in a container:
```shell
version: '3'
services:
  elasticsearch:
    image: elasticsearch:8.15.0
    container_name: es-node
    ports:
      - "9200:9200"
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
      - xpack.monitoring.templates.enabled=false
      - xpack.ml.enabled=false
      - xpack.graph.enabled=false
      - xpack.watcher.enabled=false
    networks:
      - elasticsearch_custom_bridge
networks:
  elasticsearch_custom_bridge:
    driver: bridge
```
What this code does is the following:
- The services section defines the services that will be run per container. In this case, we have a single container (a 
service) called elasticsearch. 
- Within the elasticsearch service, we define where docker is meant to pull the image from (image: elasticsearch:8.9.0, and
remember images are pulled from Docker Hub - the keyword latest won't find it, so just use 8.9.0).
- The name of the container will be es-node. So when we do docker ps, we will see a container with the name es-node.
- The environment section defines the environment variables that will be set in the container. In this case, we set the
discovery.type to single-node. This is a setting that tells ElasticSearch that it is running in a single node mode. This
is a good setting for development purposes, and it will focus our attention. Nonetheless, in a production environment, we
would want to set this to a different value. Using Elastic Search without multiple nodes would defeat the purpose of
using it in the first place. 
- Important note: Had to block xpack settings, as they were causing connectivity issues. These xpack settings are used
for security, monitoring, machine learning, graph exploration, and alerting. We will are not worried about these features
for now. Obviously in production, we would want to enable these features.
- The ports section defines the ports that will be exposed to the host machine. In this case, we expose ports 9200 and 9300.
- You could set up volumes here, to save data in between container restarts, but we will not do this.

When we have this file, we can run the following command in the terminal:
```shell
docker-compose up
```
This command will start the container with the ElasticSearch image. We can check if the container is running by running
the following command:
```shell
docker ps
```
If the container is running, we can access ElasticSearch by going to the following URL in the browser:
```shell
http://localhost:9200
```

# Setting up a project with ElasticSearch
Let's try experimenting with ElasticSearch. Perhaps do a few CRUD operations and see how it works.
ElasticSearch has Spring Data support, so we can use Spring Data ElasticSearch to make this first interaction a bit
easier, and more focused.

