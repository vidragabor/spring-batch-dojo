package hu.vidragabor.springbatchdojo.prepare.reader;

import hu.vidragabor.springbatchdojo.model.User;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class PrepareReaderConfiguration {
	
	private static final String PREPARE_COLUMNS = "id, first_name, last_name, age";
	private static final String PREPARE_TABLE_NAME = "prepare";
	private static final int DEFAULT_SIZE = 30;
	
	@Bean
	public JdbcPagingItemReader<User> prepareReader(DataSource dataSource) {
		JdbcPagingItemReaderBuilder<User> builder = new JdbcPagingItemReaderBuilder<User>()
				.name("prepareReader")
				.rowMapper(getRowMapper())
				.selectClause(getSelectClause())
				.fromClause(getFromClause())
				.sortKeys(getSortKey())
				.fetchSize(getFetchSize())
				.pageSize(getPageSize())
				.saveState(getSaveState())
				.dataSource(dataSource);
		return builder.build();
	}
	
	private RowMapper<User> getRowMapper() {
		return BeanPropertyRowMapper.newInstance(User.class);
	}
	
	private String getSelectClause() {
		return PREPARE_COLUMNS;
	}
	
	private String getFromClause() {
		return PREPARE_TABLE_NAME;
	}
	
	public Map<String, Order> getSortKey() {
		return Map.of("id", Order.ASCENDING);
	}
	
	private int getFetchSize() {
		return DEFAULT_SIZE;
	}
	
	private int getPageSize() {
		return DEFAULT_SIZE;
	}
	
	private boolean getSaveState() {
		return false;
	}
	
}
