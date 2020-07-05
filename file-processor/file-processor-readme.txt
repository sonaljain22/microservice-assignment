1) ordering of files is assumed to be irrelevant
2) 4 Filters are used to verify the files read
3) Comparator is used for sorting files accoridng to last modified time.
4) File locker is used to prevent concurrent access by multiple processes.
5) Assumption that application reads file from local and non-shared directory. Multiple instances can run together. But different MetadatStore will be required for shared file directory across servers.
6) Task executor for parallel execution. In case parallel is not supported, the pool size can be set as 1
7) Usage of Java 8 Functional interfaces and lambdas for runtime computation logic.
8) Usage of Kafka key for producing all transactions of same X_PMT_INSTD_AGT_ID on a single partition to maintain ordering.

Exception Handling:
1) Every successfully parsed record of a file is logged in the logger statement and then sent to kafka Producer.
2) Any error in processing file is logged separately.
3) Error files can be processed again by renaming the files and removing the records which are already parsed by verifying from the log file.



Improvements:
1) Register shutdown hook
2) Better error handling for IOException and Kafka Exceptions