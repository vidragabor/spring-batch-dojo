package hu.vidragabor.springbatchdojo.ftp;

import hu.vidragabor.springbatchdojo.ftp.service.FtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
public class FtpUploadTasklet implements Tasklet {
	
	@Value("${remote.dump.file.path}/${remote.dump.file.name}")
	private String origDumpFile;
	
	@Value("${ftp.file.name}")
	private String remoteFileName;
	
	private final FtpService ftpService;
	
	@Override
	public RepeatStatus execute(@NonNull StepContribution stepContribution, @NonNull ChunkContext chunkContext) {
		if (ftpService.uploadFile(new File(origDumpFile), remoteFileName)) {
			log.info("File uploading is successful!");
		} else {
			throw new IllegalStateException("Unsuccessful file uploading!");
		}
		return RepeatStatus.FINISHED;
	}
}
