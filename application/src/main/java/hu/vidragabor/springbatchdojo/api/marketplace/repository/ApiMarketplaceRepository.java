package hu.vidragabor.springbatchdojo.api.marketplace.repository;

import hu.vidragabor.springbatchdojo.api.marketplace.model.Marketplace;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApiMarketplaceRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public void save(final Marketplace marketplace) {
		jdbcTemplate.update("INSERT INTO marketplace (id, marketplace_name) VALUES (:id, :name) ON CONFLICT DO NOTHING", new BeanPropertySqlParameterSource(marketplace));
	}
	
}