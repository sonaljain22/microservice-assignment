This project is Maven based multi-module project having following 3 maven modules:

1) file-processor - Spring-boot based microservice to parse files with transaction messages and publish the transaction messages on Kafka topic.
2) transaction-processor - Spring-boot based microservice to consume transaction messages from Kafka topic, process and persist them in MongoDB.
3) transaction-api - Spring-boot based microservice to provide REST based APIs for transferring Transaction data from MongoDB to UI app or Rest client.

The key design implementations for each of these microservices are following:

A) file-processor

1) spring-integration based File handling API of spring boot is used to process files.
2) 4 Filters are used to verify and read the files. File once processed by application is not processed again.
3) Comparator is used for sorting files according to last modified time.
4) File locker is used to prevent concurrent access by multiple processes if the processes run on same server.
5) Assumption that application reads file from local and non-shared directory. Multiple instances can run together. But different MetadatStore will be required for shared file directory across servers.
6) Task executor for parallel execution. In case parallel processing is not supported, the pool size can be set as 1.
7) Usage of Java 8 Functional interfaces and lambdas for runtime computation logic.
8) Usage of Kafka key for producing all transactions of same X_PMT_INSTD_AGT_ID on a single partition to maintain ordering of the transaction processing.

Exception Handling:
1) Every successfully parsed record of a file is logged in the logger statement and then sent to kafka Producer.
2) Any error in processing file is logged separately.
3) Error files can be processed again by renaming the files and removing the records which are already parsed by verifying from the log file.



B) transaction-processor

1) Consuming all transactions of same X_PMT_INSTD_AGT_ID by a single listener thread to maintain ordering.
2) Concurrency for Kafka listeners to consume from multiple partitions simultaneously.
3) JsonDeserializer for handling mapping of string to bean. ErrorHandlingDeserializer2 for handling json mapping errors.
3) Mongo DB is used as persistent store.
4) Duplicate transaction check using unique Mongo ID
5) Annotation based validation for non-nullable fields in message.

Exception Handling:
1) Kafka offset is committed manually depending upon state of execution for transaction message read from kafak topic.
2) No retry of message in case of non-recoverable exception for a transaction message. For instance, failed validation or duplicate id.
3) Retry in case of recoverable error like mongo connection error.


C) transaction-api

1) Authorization using OAUTH2 based resource server setup in spring boot app
2) Security configuration setup in applciation.yml
3) Used PagingAndSortingRepository to extend TxnRepo.
4) Provided two API methods, one to get individual txn by id and other one to get Pageable data of transactions. 



Scope of improvements in the applications:
1) application.properties are not provided for file-processor and transaction-processor. Default values to be added.
2) Register shutdown hook for all services for graceful shutdown.
3) Add unit tests for all classes.
4) SSL for http and Kafka.
5) Use of ConsumerRecord in kafka listener methods
6) Password protection for mongo db connnectivity.