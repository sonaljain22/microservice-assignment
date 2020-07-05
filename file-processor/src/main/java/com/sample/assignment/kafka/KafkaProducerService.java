/**
 * 
 */
package com.sample.assignment.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author sonal
 *
 */
@Service
public class KafkaProducerService {

	private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	private final String SUCCESS_DEBUG_LOG_MESSAGE = "Transaction record published on Kafka topic, message : {0}, offset : {1}";

	private final String FAILURE_ERROR_LOG_MESSAGE = "Failure in publishing record on kafka topic, key : {0}, message : {1}, Exception : {2}";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${kafka.send.topic}")
	private String kafkaTopic;

	public void sendMessage(String key, String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaTopic, key, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.debug(SUCCESS_DEBUG_LOG_MESSAGE, message, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error(FAILURE_ERROR_LOG_MESSAGE, key, message, ex);
			}
		});
	}

}
