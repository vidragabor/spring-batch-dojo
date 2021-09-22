package hu.vidragabor.springbatchdojo.ftp.decider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.job.flow.FlowExecutionStatus.COMPLETED;
import static org.springframework.batch.core.job.flow.FlowExecutionStatus.STOPPED;

@Slf4j
@Component
public class FtpUploadDecider implements JobExecutionDecider {
	
	@Value("${ftp.upload.threshold}")
	private int deciderThreshold;
	
	private boolean shouldUpload() {
		final int randomInt = RandomUtils.nextInt(1, 10);
		final boolean shouldUpload = randomInt >= deciderThreshold;
		log.info("randomInt: {}, shouldUpload: {}", randomInt, shouldUpload);
		return false;
	}
	
	@NonNull
	@Override
	public FlowExecutionStatus decide(@NonNull JobExecution jobExecution, StepExecution stepExecution) {
		return shouldUpload() ? COMPLETED : STOPPED;
	}
}
