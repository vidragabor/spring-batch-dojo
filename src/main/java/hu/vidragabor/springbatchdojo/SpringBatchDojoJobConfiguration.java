package hu.vidragabor.springbatchdojo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchDojoJobConfiguration {
	
	private final JobBuilderFactory jobBuilderFactory;
	private final Step csvStep;
	
	@Bean
	public Job importUserJob() {
		log.info("Creating \"importUserJob\" job.");
		return jobBuilderFactory
				.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.start(csvStep)
				.build();
	}
}
