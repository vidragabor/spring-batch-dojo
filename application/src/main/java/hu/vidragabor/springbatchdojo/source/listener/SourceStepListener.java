package hu.vidragabor.springbatchdojo.source.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SourceStepListener implements StepExecutionListener {
	
	@Override
	public void beforeStep(@NonNull StepExecution stepExecution) {
		log.info("Dumping source starting...");
	}
	
	@Override
	public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
		log.info("Dumping source finished.");
		return ExitStatus.COMPLETED;
	}
	
}
