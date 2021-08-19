package hu.vidragabor.springbatchdojo.remoteload.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RemoteLoadRepository {
	
	@Value("${remote.dump.file.path}/${remote.dump.file.name}")
	private String fileName;
	
	private final JdbcTemplate jdbcTemplate;
	
	public void loadDumpIntoTable() {
		jdbcTemplate.execute(generateLoadSql());
	}
	
	private String generateLoadSql() {
		return "COPY \"source\" FROM '/" + fileName + "' DELIMITER E'\\t'";
	}
	
}
