/**
 * 
 */
package com.sample.assignment.file.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.sample.assignment.kafka.KafkaProducerService;

/**
 * @author sonal
 *
 */
@Service
public class FileProcessService {

	private final Logger logger = LoggerFactory.getLogger(FileProcessService.class);

	private final String PMT_INSTD_AGT = "X_PMT_INSTD_AGT_ID";
	private final int PMT_INSTD_AGT_LENGTH = 22;
	private final int PMT_INSTD_AGT_VALUE_LENGTH = 9;

	private final String TXN_INFO_LOG_MESSAGE = "{0} : Transaction record sent to Processor Application : [{1}]";

	private final String FILE_ERROR_LOG_MESSAGE = "Error in parsing Transaction file : [{0}], Exception : {1}";

	private final Predicate<String> filterPredicate = (str) -> !str.isEmpty() && str.contains(PMT_INSTD_AGT)
			&& str.length() > str.indexOf(PMT_INSTD_AGT) + PMT_INSTD_AGT_LENGTH + PMT_INSTD_AGT_VALUE_LENGTH;

	private final Function<String, String> keyFunction = (str) -> {
		int keyPosition = str.indexOf(PMT_INSTD_AGT) + PMT_INSTD_AGT_LENGTH;
		return str.substring(keyPosition, keyPosition + PMT_INSTD_AGT_VALUE_LENGTH);
	};

	@Autowired
	private KafkaProducerService producerService;

	public void processFile(Message<File> message) {
		File file = message.getPayload();
		try (Stream<String> lines = new BufferedReader(new FileReader(file)).lines();) {
			lines.filter(str -> filterPredicate.test(str)).forEach(str -> {
				logger.info(TXN_INFO_LOG_MESSAGE, file.getName(), str);
				producerService.sendMessage(keyFunction.apply(str), str);
			});
		} catch (IOException | RuntimeException ex) {
			logger.error(FILE_ERROR_LOG_MESSAGE, file.getName(), ex);
		}

	}

}
