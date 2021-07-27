package hu.vidragabor.springbatchdojo.component;

import hu.vidragabor.springbatchdojo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobCompletionListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			
			jdbcTemplate.query("SELECT first_name, last_name, age FROM user",
					(rs, row) -> new User(rs.getString(1), rs.getString(2), rs.getInt(3))
			).forEach(person -> log.info("Found <" + person + "> in the database."));
		}
	}
}
