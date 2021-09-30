package hu.vidragabor.springbatchdojo.remotedump.reader;

import hu.vidragabor.common.model.User;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class RemoteDumpReaderConfiguration {
	
	private static final String PREPARE_COLUMNS = "id, first_name, last_name, age";
	private static final String PREPARE_TABLE_NAME = "remote_source";
	private static final int DEFAULT_SIZE = 30;
	
	@Bean
	public JdbcPagingItemReader<User> remoteDumpReader(@Qualifier("remoteDataSource") DataSource dataSource) {
		JdbcPagingItemReaderBuilder<User> builder = new JdbcPagingItemReaderBuilder<User>()
				.name("remoteDumpReader")
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
