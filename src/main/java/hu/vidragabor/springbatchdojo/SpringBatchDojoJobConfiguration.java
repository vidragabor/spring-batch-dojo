package hu.vidragabor.springbatchdojo;

import hu.vidragabor.springbatchdojo.listener.JobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchDojoJobConfiguration {
	
	@Value("${spring.application.name}")
	private String appName;
	
	private final JobBuilderFactory jobBuilderFactory;
	private final JobListener jobListener;
	private final Step prepareStep;
	private final Step createFileStep;
	private final Step userStoreStep;
	
	@Bean
	public Job userStoreJob() {
		log.info("Creating \"userStoreJob\" job.");
		return jobBuilderFactory
				.get(appName)
				.incrementer(new RunIdIncrementer())
				.listener(jobListener)
				.start(prepareStep)
				.next(createFileStep)
				.next(userStoreStep)
				.build();
	}
}
