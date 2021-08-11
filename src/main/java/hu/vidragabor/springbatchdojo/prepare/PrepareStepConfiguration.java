package hu.vidragabor.springbatchdojo.prepare;

import hu.vidragabor.springbatchdojo.prepare.model.Prepare;
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
	private final JdbcPagingItemReader<Prepare> prepareReader;
	private final PrepareWriter prepareWriter;
	
	@Bean
	public Step prepareStep() {
		return stepBuilderFactory
				.get("prepareStep")
				.<Prepare, Prepare>chunk(1)
				.reader(prepareReader)
				.writer(prepareWriter)
				.build();
	}
	
}
