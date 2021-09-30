package hu.vidragabor.springbatchdojo.api.marketplace.reader;

import hu.vidragabor.springbatchdojo.configuration.client.MockarooClient;
import hu.vidragabor.springbatchdojo.api.marketplace.model.Marketplace;
import hu.vidragabor.springbatchdojo.api.reader.BufferedApiReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiMarketplaceReader extends BufferedApiReader<Marketplace> {
	public ApiMarketplaceReader(MockarooClient mockarooClient) {
		super(mockarooClient);
	}
	
	@Override
	protected List<Marketplace> fetchItems() {
		return mockarooClient.getMarketplace(key);
	}
}
