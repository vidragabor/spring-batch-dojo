package hu.vidragabor.springbatchdojo.remotedump.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class RemoteRepository {
	
	@Autowired
	@Qualifier("remoteNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String COUNT_REMOTE_USER = "SELECT COUNT(id) FROM \"remote_source\"";
	
	public Integer countRemoteUser() {
		return jdbcTemplate.queryForObject(COUNT_REMOTE_USER, new HashMap<>(), Integer.class);
	}
	
}
