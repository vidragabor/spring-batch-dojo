package hu.vidragabor.springbatchdojo.source.writer;

import hu.vidragabor.springbatchdojo.source.model.Source;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

@Configuration
public class SourceWriterConfiguration {
	
	@Value("${fileName}")
	private String fileName;
	
	@Bean
	public FlatFileItemWriter<Source> writer() throws MalformedURLException {
		return new FlatFileItemWriterBuilder<Source>()
				.name("batchDojoCsvWriter")
				.resource(new FileUrlResource(fileName))
				.lineAggregator(getDelimitedLineAggregator())
				.build();
	}
	
	private DelimitedLineAggregator<Source> getDelimitedLineAggregator() {
		final DelimitedLineAggregator<Source> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
		lineAggregator.setFieldExtractor(getBeanWrapperFieldExtractor());
		return lineAggregator;
	}
	
	private BeanWrapperFieldExtractor<Source> getBeanWrapperFieldExtractor() {
		final BeanWrapperFieldExtractor<Source> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[]{"lastName", "firstName", "age"});
		return fieldExtractor;
	}
}
