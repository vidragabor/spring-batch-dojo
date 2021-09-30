package hu.vidragabor.springbatchdojo.api.status.repository;

import hu.vidragabor.springbatchdojo.api.status.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApiStatusRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public void save(Status status) {
		jdbcTemplate.update("INSERT INTO listing_status (id, status_name) VALUES (:id, :name) ON CONFLICT DO NOTHING", new BeanPropertySqlParameterSource(status));
	}
}