/**
 * 
 */
package com.sample.assignment.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoException;
import com.sample.assignment.transaction.bean.TransactionMessageBean;

/**
 * @author sonal
 *
 */
@Service
public class KafkaConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	private final long sleepTime = 5000;

	private final String TXN_INFO_LOG_MESSAGE = "Transaction message processed by consumer Application : [{0}]";

	private final String TXN_ERROR_LOG_MESSAGE = "Error in processing Transaction message : [{0}], Exception message : {1}, Exception : {2}";

	private final String TXN_WARN_LOG_MESSAGE = "Message to be re-tried from Kafka, Mongo error in processing Transaction message : [{0}], Exception message : {1}, Exception : {2}";

	@Autowired
	private MongoTemplate mongoTemplate;

	@KafkaListener(topics = "${kafka.receive.topic}", containerFactory = "kafkaListenerContainerFactory")
	public void messageListener(TransactionMessageBean message, Acknowledgment acknowledgment) {
		try {
			mongoTemplate.insert(message);
			this.processTransactionMessage(message);
			acknowledgment.acknowledge();
		} catch (NullPointerException | DeserializationException | IllegalArgumentException | DuplicateKeyException ex) {
			logger.error(TXN_ERROR_LOG_MESSAGE, message, ex.getMessage(), ex);
			acknowledgment.acknowledge();
		} catch (MongoException ex) {
			logger.warn(TXN_WARN_LOG_MESSAGE, message, ex.getMessage(), ex);
			acknowledgment.nack(sleepTime);
		}
	}

	private void processTransactionMessage(TransactionMessageBean message) {
		logger.debug(TXN_INFO_LOG_MESSAGE, message);
	}
}
