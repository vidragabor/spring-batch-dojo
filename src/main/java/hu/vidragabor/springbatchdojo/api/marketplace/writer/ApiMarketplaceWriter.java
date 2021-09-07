package hu.vidragabor.springbatchdojo.api.marketplace.writer;

import hu.vidragabor.springbatchdojo.api.marketplace.model.Marketplace;
import hu.vidragabor.springbatchdojo.api.marketplace.repository.ApiMarketplaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiMarketplaceWriter implements ItemWriter<Marketplace> {
	
	private final ApiMarketplaceRepository apiMarketplaceRepository;
	
	@Override
	public void write(final List<? extends Marketplace> marketplaces) {
		marketplaces.forEach(apiMarketplaceRepository::save);
	}
	
}
