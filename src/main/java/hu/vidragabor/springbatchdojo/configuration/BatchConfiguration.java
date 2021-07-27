package hu.vidragabor.springbatchdojo.configuration;

import hu.vidragabor.springbatchdojo.component.JobCompletionListener;
import hu.vidragabor.springbatchdojo.component.UserItemProcessor;
import hu.vidragabor.springbatchdojo.dto.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@StepScope
	@Bean
	public FlatFileItemReader<User> reader(@Value("#{jobParameters[inputFilePath]}") String filePath) {
		return new FlatFileItemReaderBuilder<User>()
				.name("UserItemReader")
				.delimited()
				.names(new String[]{"firstName", "lastName", "age"})
				.resource(new FileSystemResource(filePath))
				.fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
					setTargetType(User.class);
				}})
				.build();
	}
	
	@Bean
	public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<User>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO user (first_name, last_name, age) VALUES (:firstName, :lastName, :age)")
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
	public Step step1(JdbcBatchItemWriter<User> writer, UserItemProcessor processor, FlatFileItemReader<User> reader) {
		return stepBuilderFactory.get("step1")
				.<User, User>chunk(2)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}