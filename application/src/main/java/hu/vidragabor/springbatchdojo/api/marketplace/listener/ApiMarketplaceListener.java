package hu.vidragabor.springbatchdojo.api.marketplace.listener;

import hu.vidragabor.springbatchdojo.api.marketplace.model.Marketplace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiMarketplaceListener implements ItemReadListener<Marketplace> {
	@Override
	public void beforeRead() {
	}
	
	@Override
	public void afterRead(@NonNull final Marketplace marketplace) {
		log.info("Processing {} ", marketplace);
	}
	
	@Override
	public void onReadError(Exception e) {
	}
}
