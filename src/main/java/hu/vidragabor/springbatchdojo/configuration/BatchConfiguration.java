package hu.vidragabor.springbatchdojo.configuration;

import hu.vidragabor.springbatchdojo.component.JobCompletionListener;
import hu.vidragabor.springbatchdojo.component.UserItemProcessor;
import hu.vidragabor.springbatchdojo.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {
	
	private static final String RESOURCE_FILE = "feladat_1.csv";
	private static final String DELIMITER = DelimitedLineTokenizer.DELIMITER_TAB;
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public UserItemProcessor processor() {
		return new UserItemProcessor();
	}
	
	@Bean
	public FlatFileItemReader<User> reader() {
		final BeanWrapperFieldSetMapper<User> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(User.class);
		
		return new FlatFileItemReaderBuilder<User>()
				.name("userItemReader")
				.resource(new ClassPathResource(RESOURCE_FILE))
				.linesToSkip(1)
				.delimited()
				.delimiter(DELIMITER)
				.names("lastName", "firstName", "age")
				.fieldSetMapper(beanWrapperFieldSetMapper)
				.build();
	}
	
	@Bean
	public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<User>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO \"user\" (first_name, last_name, age) VALUES (:firstName, :lastName, :age)")
				.dataSource(dataSource)
				.build();
	}
	
	@Bean
	public Job importUserJob(JobCompletionListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<User> writer) {
		return stepBuilderFactory.get("step1")
				.<User, User>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}
}