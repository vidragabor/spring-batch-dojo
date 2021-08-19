package hu.vidragabor.springbatchdojo.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.nio.file.Path;

@Slf4j
@Component
public class JobListener extends JobExecutionListenerSupport {
	
	@Value("${remote.dump.file.path}")
	private String path;
	
	@SneakyThrows
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			if (FileSystemUtils.deleteRecursively(Path.of(path))) {
				log.info("Delete \"{}\" folder!", path);
			}
		}
	}
	
}