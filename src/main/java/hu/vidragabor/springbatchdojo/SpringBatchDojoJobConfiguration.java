package hu.vidragabor.springbatchdojo;

import hu.vidragabor.springbatchdojo.ftp.decider.FtpUploadDecider;
import hu.vidragabor.springbatchdojo.listener.JobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableFeignClients
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchDojoJobConfiguration {
	
	@Value("${spring.application.name}")
	private String appName;
	
	private final JobBuilderFactory jobBuilderFactory;
	private final JobListener jobListener;
	private final Step remoteDumpStep;
	private final Step remoteLoadStep;
	private final Step createFileStep;
	private final Step userStoreStep;
	private final Step apiMarketplaceStep;
	private final Step apiStatusStep;
	private final Step ftpUploadStep;
	
	@Bean
	public Job userStoreJob() {
		log.info("Creating \"{}\" job.", appName);
		return jobBuilderFactory
				.get(appName)
				.incrementer(new RunIdIncrementer())
				.start(remoteDumpStep)
				.next(remoteLoadStep)
				.next(createFileStep)
				.next(userStoreStep)
				.next(apiMarketplaceStep)
				.next(apiStatusStep)
				.next(ftpUploadStep)
				.listener(jobListener)
				.build();
	}
}
