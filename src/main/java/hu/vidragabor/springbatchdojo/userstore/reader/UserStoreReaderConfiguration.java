package hu.vidragabor.springbatchdojo.userstore.reader;

import hu.vidragabor.springbatchdojo.userstore.model.User;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

@Configuration
public class UserStoreReaderConfiguration {
	
	@Value("${fileName}")
	private String fileName;
	
	@Bean
	public FlatFileItemReader<User> batchDojoCsvReader() throws MalformedURLException {
		return new FlatFileItemReaderBuilder<User>()
				.name("batchDojoCsvReader")
				.resource(new FileUrlResource(fileName))
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
	
}
