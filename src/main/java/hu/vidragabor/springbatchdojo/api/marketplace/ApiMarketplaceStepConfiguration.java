package hu.vidragabor.springbatchdojo.api.marketplace;

import hu.vidragabor.springbatchdojo.api.marketplace.listener.ApiMarketplaceListener;
import hu.vidragabor.springbatchdojo.api.marketplace.model.Marketplace;
import hu.vidragabor.springbatchdojo.api.marketplace.reader.ApiMarketplaceReader;
import hu.vidragabor.springbatchdojo.api.marketplace.writer.ApiMarketplaceWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiMarketplaceStepConfiguration {
	
	private final StepBuilderFactory stepBuilderFactory;
	private final ApiMarketplaceReader apiMarketplaceReader;
	private final ApiMarketplaceWriter apiMarketplaceWriter;
	private final ApiMarketplaceListener apiMarketplaceListener;
	
	@Bean
	public Step apiMarketplaceStep() {
		return stepBuilderFactory
				.get("apiMarketplaceStep")
				.<Marketplace, Marketplace>chunk(30)
				.faultTolerant()
				.skipLimit(1)
				.skip(Exception.class)
				.reader(apiMarketplaceReader)
				.listener(apiMarketplaceListener)
				.writer(apiMarketplaceWriter)
				.build();
	}
}