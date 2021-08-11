package hu.vidragabor.springbatchdojo.userstore.repositroy;

import hu.vidragabor.springbatchdojo.userstore.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String INSERT_USER = "INSERT INTO \"user\" (first_name, last_name, age) values (:firstName, :lastName, :age) ON CONFLICT DO NOTHING";
	
	public void save(final User user) {
		jdbcTemplate.update(INSERT_USER, new BeanPropertySqlParameterSource(user));
	}
}
