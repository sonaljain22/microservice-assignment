/**
 * 
 */
package com.sample.assignment.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * @author sonal
 *
 */
@Configuration
public class MongoConfig {

	@Value(value = "${mongo.uri}")
	private String mongoUri;

	@Value(value = "${mongo.db}")
	private String mongoDb;

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(mongoUri);

	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoClient(), mongoDb);
	}
}
