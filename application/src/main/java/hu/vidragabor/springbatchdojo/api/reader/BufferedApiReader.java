package hu.vidragabor.springbatchdojo.api.reader;

import hu.vidragabor.springbatchdojo.configuration.client.MockarooClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@RequiredArgsConstructor
public abstract class BufferedApiReader<T> implements ItemReader<T> {
	
	private final Queue<T> items = new ConcurrentLinkedQueue<>();
	private long total;
	private long processedCount;
	@Value("${mockaroo.key}")
	protected String key;
	protected final MockarooClient mockarooClient;
	
	@Override
	public T read() {
		initMarketplaces();
		processedCount++;
		return items.poll();
	}
	
	private void initMarketplaces() {
		if (items.isEmpty() && (total > processedCount || total == 0)) {
			items.addAll(fetchItems());
			total = items.size();
			log.info("Fetched {} items.", total);
		}
	}
	
	protected abstract List<T> fetchItems();
}
