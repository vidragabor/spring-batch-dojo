package hu.vidragabor.springbatchdojo.api.status.listener;

import hu.vidragabor.springbatchdojo.api.status.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiStatusListener implements ItemReadListener<Status> {
	@Override
	public void beforeRead() {
	}
	
	@Override
	public void afterRead(final Status status) {
		log.info("Processing {} ", status);
	}
	
	@Override
	public void onReadError(Exception e) {
	}
}
