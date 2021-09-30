package hu.vidragabor.springbatchdojo.api.status.reader;

import hu.vidragabor.springbatchdojo.api.reader.BufferedApiReader;
import hu.vidragabor.springbatchdojo.api.status.model.Status;
import hu.vidragabor.springbatchdojo.configuration.client.MockarooClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiStatusReader extends BufferedApiReader<Status> {
	
	public ApiStatusReader(MockarooClient mockarooClient) {
		super(mockarooClient);
	}
	
	@Override
	protected List<Status> fetchItems() {
		return mockarooClient.getListingStatus(key);
	}
}
