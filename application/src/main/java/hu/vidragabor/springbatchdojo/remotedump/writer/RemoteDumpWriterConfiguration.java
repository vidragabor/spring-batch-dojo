package hu.vidragabor.springbatchdojo.remotedump.writer;

import hu.vidragabor.common.model.User;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_TAB;

@Configuration
public class RemoteDumpWriterConfiguration {
	
	@Value("${remote.dump.file.path}/${remote.dump.file.name}")
	private String fileName;
	
	@Bean
	@StepScope
	public FlatFileItemWriter<User> remoteDumpWriter() {
		return new FlatFileItemWriterBuilder<User>()
				.name("remoteDumpWriter")
				.resource(new FileSystemResource(fileName))
				.delimited()
				.delimiter(DELIMITER_TAB)
				.names("id", "firstName", "lastName", "age")
				.build();
	}
	
}
