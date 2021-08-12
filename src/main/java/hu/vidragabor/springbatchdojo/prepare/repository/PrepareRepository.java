package hu.vidragabor.springbatchdojo.prepare.repository;

import hu.vidragabor.springbatchdojo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrepareRepository {
	
	private final @Qualifier("prepareNamedParameterJdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String INSERT_SOURCE = "INSERT INTO \"source\" (first_name, last_name, age) values (:firstName, :lastName, :age) ON CONFLICT DO NOTHING";
	
	public void save(final User user) {
		jdbcTemplate.update(INSERT_SOURCE, new BeanPropertySqlParameterSource(user));
	}
	
}
