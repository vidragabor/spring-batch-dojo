package hu.vidragabor.springbatchdojo.userstore.reader;

import hu.vidragabor.common.model.User;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class UserStoreReaderConfiguration {
	
	private String csvPath;
	
	@Bean
	@StepScope
	public FlatFileItemReader<User> batchDojoCsvReader() {
		return new FlatFileItemReaderBuilder<User>()
				.name("batchDojoCsvReader")
				.resource(new PathResource(csvPath))
				//.linesToSkip(1)
				.delimited()
				.delimiter(DelimitedLineTokenizer.DELIMITER_TAB)
				.names("lastName", "firstName", "age")
				.fieldSetMapper(provideFieldSetMapper())
				.build();
	}
	
	private BeanWrapperFieldSetMapper<User> provideFieldSetMapper() {
		final BeanWrapperFieldSetMapper<User> userBeanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		userBeanWrapperFieldSetMapper.setTargetType(User.class);
		return userBeanWrapperFieldSetMapper;
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		csvPath = stepExecution
				.getJobExecution()
				.getExecutionContext()
				.getString("csvPath");
	}
	
}
