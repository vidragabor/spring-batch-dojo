package hu.vidragabor.springbatchdojo.remotedump;

import hu.vidragabor.common.model.User;
import hu.vidragabor.springbatchdojo.remotedump.partitioner.RemotePartitioner;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class RemoteDumpStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final FlatFileItemWriter<User> remoteDumpWriter;
	private final JdbcPagingItemReader<User> remoteDumpReader;
	private final RemotePartitioner remotePartitioner;
	
	@Bean
	public Step remoteDumpStep() {
		return stepBuilderFactory
				.get("remoteDumpStep")
				.partitioner(slaveStep().getName(), remotePartitioner)
				.step(slaveStep())
				.gridSize(10)
				.taskExecutor(jobTaskExecutor())
				.build();
	}
	
	@Bean
	public Step slaveStep() {
		return stepBuilderFactory
				.get("remoteSlaveDumpStep")
				.<User, User>chunk(10)
				.reader(remoteDumpReader)
				.writer(remoteDumpWriter)
				.build();
	}
	
	@Bean
	public TaskExecutor jobTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
}
