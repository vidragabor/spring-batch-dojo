package hu.vidragabor.springbatchdojo.source.writer;

import hu.vidragabor.springbatchdojo.source.model.Source;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDateTime;

@Configuration
public class SourceWriterConfiguration {
	
	private static final String HEADER = "last_name\tfirst_name\tage";
	
	@Value("${file.path}")
	private String filePath;
	
	private String csvPath;
	
	@Bean
	public FlatFileItemWriter<Source> writer() {
		csvPath = generateCsvPath();
		return new FlatFileItemWriterBuilder<Source>()
				.name("batchDojoCsvWriter")
				.resource(new FileSystemResource(csvPath))
				.lineAggregator(getDelimitedLineAggregator())
				.headerCallback(initHeader())
				.build();
	}
	
	private String generateCsvPath() {
		return filePath + LocalDateTime.now().toString().replace(":", "_") + ".csv";
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
	
	private FlatFileHeaderCallback initHeader() {
		return writer -> writer.write(HEADER);
	}
	
	@AfterStep
	public void afterStep(StepExecution stepExecution) {
		stepExecution
				.getJobExecution()
				.getExecutionContext()
				.put("csvPath", csvPath);
	}
}
