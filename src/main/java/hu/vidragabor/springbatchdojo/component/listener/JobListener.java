package hu.vidragabor.springbatchdojo.component.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class JobListener extends JobExecutionListenerSupport {
	
	@Value("${fileName}")
	private String fileName;
	
	@SneakyThrows
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			deleteFile();
		}
	}
	
	public void deleteFile() throws IOException {
		if (Files.deleteIfExists(Path.of(fileName))) {
			log.info("Temporary .csv file deleted!");
		}
	}
}