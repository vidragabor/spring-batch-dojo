package hu.vidragabor.springbatchdojo.remoteload;

import hu.vidragabor.springbatchdojo.remoteload.repository.RemoteLoadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemoteLoadTasklet implements Tasklet {
	
	private final RemoteLoadRepository remoteLoadRepository;
	
	@Override
	public RepeatStatus execute(@NonNull StepContribution stepContribution, @NonNull ChunkContext chunkContext) throws Exception {
		log.info("Loading dump to table");
		final long copyCount = remoteLoadRepository.loadDumpIntoTable();
		log.info("Copied {} items to database.", copyCount);
		return RepeatStatus.FINISHED;
	}
	
}
