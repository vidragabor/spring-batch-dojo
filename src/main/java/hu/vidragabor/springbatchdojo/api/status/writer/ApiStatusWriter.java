package hu.vidragabor.springbatchdojo.api.status.writer;

import hu.vidragabor.springbatchdojo.api.status.model.Status;
import hu.vidragabor.springbatchdojo.api.status.repository.ApiStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiStatusWriter implements ItemWriter<Status> {
	
	private final ApiStatusRepository apiStatusRepository;
	
	@Override
	public void write(List<? extends Status> statuses) throws Exception {
		statuses.forEach(apiStatusRepository::save);
	}
}
