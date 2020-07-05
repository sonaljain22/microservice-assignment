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


Improvements:
1) Use of ConsumerRecord in kafka listener methods
2) Password protection for mongo db connnectivity.
3) SSL for kafka
4) Register shutdown hook.
