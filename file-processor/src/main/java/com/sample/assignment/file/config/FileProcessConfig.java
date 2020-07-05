/**
 * 
 */
package com.sample.assignment.file.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.FileSystemPersistentAcceptOnceFileListFilter;
import org.springframework.integration.file.filters.IgnoreHiddenFileListFilter;
import org.springframework.integration.file.filters.LastModifiedFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.locking.NioFileLocker;
import org.springframework.integration.metadata.PropertiesPersistingMetadataStore;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author sonal
 *
 */
@Configuration
@EnableIntegration
public class FileProcessConfig {

	@Value(value = "${file.dir.path}")
	private String directoryPath;
	
	@Value(value = "${executor.core.pool.size}")
	private int corePoolSize;
	
	@Value(value = "${executor.max.pool.size}")
	private int maxPoolSize;

	@Bean
	public MessageChannel fileChannel() {
		return new DirectChannel();
	}

	@Bean
	@InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "10000", taskExecutor="taskExecutor"))
	public MessageSource<File> fileReadingMessageSource() {
		CompositeFileListFilter<File> filters = new CompositeFileListFilter<>();
		filters.addFilter(new SimplePatternFileListFilter("*.json"));
		filters.addFilter(new LastModifiedFileListFilter());
		filters.addFilter(new IgnoreHiddenFileListFilter());
		filters.addFilter(
				new FileSystemPersistentAcceptOnceFileListFilter(new PropertiesPersistingMetadataStore(), ""));

		FileReadingMessageSource source = new FileReadingMessageSource(((f1, f2) -> {
			return Long.compare(f1.lastModified(), f2.lastModified());
		}));
		source.setDirectory(new File(directoryPath));
		source.setLocker(new NioFileLocker());
		source.setFilter(filters);

		return source;
	}

	@Bean
	public IntegrationFlow processFileFlow() {
		return IntegrationFlows.from("fileChannel").handle("fileProcessService", "processFile").get();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setThreadNamePrefix("file_processor_executor");
		executor.initialize();
		return executor;
	}

}
