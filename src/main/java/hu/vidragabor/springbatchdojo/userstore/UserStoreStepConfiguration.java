package hu.vidragabor.springbatchdojo.userstore;

import hu.vidragabor.springbatchdojo.userstore.listener.UserStoreListener;
import hu.vidragabor.springbatchdojo.userstore.model.User;
import hu.vidragabor.springbatchdojo.userstore.processor.UserStoreProcessor;
import hu.vidragabor.springbatchdojo.userstore.reader.UserStoreReaderConfiguration;
import hu.vidragabor.springbatchdojo.userstore.writer.UserStoreWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserStoreStepConfiguration {
	private final StepBuilderFactory stepBuilderFactory;
	private final FlatFileItemReader<User> userStoreReader;
	private final UserStoreProcessor userStoreProcessor;
	private final UserStoreWriter userStoreWriter;
	private final UserStoreListener userStoreListener;
	private final UserStoreReaderConfiguration userStoreReaderConfiguration;
	
	@Bean
	public Step userStoreStep() {
		return stepBuilderFactory
				.get("userStoreStep")
				.<User, User>chunk(1)
				.reader(userStoreReader)
				.processor(userStoreProcessor)
				.listener(userStoreReaderConfiguration)
				.listener(userStoreListener)
				.writer(userStoreWriter)
				.faultTolerant()
				.skipLimit(3)
				.skip(Exception.class)
				.build();
	}
}
