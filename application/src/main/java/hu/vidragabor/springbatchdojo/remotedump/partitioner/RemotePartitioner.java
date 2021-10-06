package hu.vidragabor.springbatchdojo.remotedump.partitioner;

import hu.vidragabor.springbatchdojo.remotedump.repository.RemoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RemotePartitioner implements Partitioner {
	
	private final RemoteRepository remoteRepository;
	
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		
		final Integer count = remoteRepository.countRemoteUser();
		final int total = count / gridSize;
		
		final Map<String, ExecutionContext> result = new HashMap<>();
		int loop = 1;
		
		while (loop <= total) {
			final int max = loop * gridSize;
			final int min = max - gridSize + 1;
			
			final ExecutionContext executionContext = new ExecutionContext();
			executionContext.putInt("min", min);
			executionContext.putInt("max", max);
			result.put("partition" + loop, executionContext);
			++loop;
		}
		return result;
	}
}
