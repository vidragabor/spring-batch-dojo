package hu.vidragabor.springbatchdojo.ftp;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FtpUploadStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final FtpUploadTasklet ftpUploadTasklet;
	
	@Bean
	public Step ftpUploadStep() {
		return stepBuilderFactory
				.get("ftpUploadStep")
				.tasklet(ftpUploadTasklet)
				.build();
	}
}
