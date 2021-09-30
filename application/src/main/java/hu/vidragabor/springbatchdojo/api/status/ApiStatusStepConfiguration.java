package hu.vidragabor.springbatchdojo.api.status;

import hu.vidragabor.springbatchdojo.api.status.listener.ApiStatusListener;
import hu.vidragabor.springbatchdojo.api.status.model.Status;
import hu.vidragabor.springbatchdojo.api.status.reader.ApiStatusReader;
import hu.vidragabor.springbatchdojo.api.status.writer.ApiStatusWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiStatusStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ApiStatusListener apiStatusListener;
	private final ApiStatusReader apiStatusReader;
	private final ApiStatusWriter apiStatusWriter;
	
	@Bean
	public Step apiStatusStep() {
		return stepBuilderFactory
				.get("apiStatusStep")
				.<Status, Status>chunk(30)
				.faultTolerant()
				.skipLimit(1)
				.skip(Exception.class)
				.reader(apiStatusReader)
				.listener(apiStatusListener)
				.writer(apiStatusWriter)
				.build();
	}
	
}