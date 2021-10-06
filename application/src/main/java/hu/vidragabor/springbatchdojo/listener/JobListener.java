package hu.vidragabor.springbatchdojo.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class JobListener extends JobExecutionListenerSupport {
	
	@Value("${remote.dump.file.delete}")
	private boolean fileDeletable;
	
	@Value("${remote.dump.file.path}")
	private String path;
	
	@Value("${remote.dump.file.name}")
	private String fileName;
	
	@SneakyThrows
	@Override
	public void beforeJob(JobExecution jobExecution) {
		Files.createDirectory(Paths.get(path));
		FileUtils.createNewFile(new File(path, fileName));
	}
	
	@SneakyThrows
	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("jobExecution.getStatus(): " + jobExecution.getStatus());
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			if (fileDeletable) {
				log.info("Remote dump file is deletable.");
				if (FileSystemUtils.deleteRecursively(Path.of(path))) {
					log.info("Delete \"{}\" folder!", path);
				}
			} else {
				log.info("Remote dump file is not deletable.");
			}
		}
	}
}