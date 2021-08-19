package hu.vidragabor.springbatchdojo.remotedump;

import hu.vidragabor.springbatchdojo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RemoteDumpStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final FlatFileItemWriter<User> remoteDumpWriter;
	private final JdbcPagingItemReader<User> remoteDumpReader;
	
	@Bean
	public Step remoteDumpStep() {
		return stepBuilderFactory
				.get("remoteDumpStep")
				.<User, User>chunk(30)
				.faultTolerant()
				.skipLimit(3)
				.skip(Exception.class)
				.reader(remoteDumpReader)
				.writer(remoteDumpWriter)
				.build();
	}
	
}
