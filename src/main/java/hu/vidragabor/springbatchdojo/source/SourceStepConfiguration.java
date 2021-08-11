package hu.vidragabor.springbatchdojo.source;

import hu.vidragabor.springbatchdojo.source.listener.SourceStepListener;
import hu.vidragabor.springbatchdojo.source.model.Source;
import hu.vidragabor.springbatchdojo.source.writer.SourceWriterConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SourceStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final JdbcPagingItemReader<Source> sourceReader;
	private final FlatFileItemWriter<Source> csvFileFromSourceWriterConfiguration;
	private final SourceWriterConfiguration sourceWriterConfiguration;
	private final SourceStepListener sourceStepListener;
	
	@Bean
	public Step createFileStep() {
		return stepBuilderFactory
				.get("createFileStep")
				.<Source, Source>chunk(1)
				.reader(sourceReader)
				.writer(csvFileFromSourceWriterConfiguration)
				.listener(sourceWriterConfiguration)
				.listener(sourceStepListener)
				.build();
	}
}
