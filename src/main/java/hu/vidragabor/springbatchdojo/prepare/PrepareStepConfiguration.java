package hu.vidragabor.springbatchdojo.prepare;

import hu.vidragabor.springbatchdojo.model.User;
import hu.vidragabor.springbatchdojo.prepare.writer.PrepareWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PrepareStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final JdbcPagingItemReader<User> prepareReader;
	private final PrepareWriter prepareWriter;
	
	@Bean
	public Step prepareStep() {
		return stepBuilderFactory
				.get("prepareStep")
				.<User, User>chunk(1)
				.reader(prepareReader)
				.writer(prepareWriter)
				.build();
	}
	
}
