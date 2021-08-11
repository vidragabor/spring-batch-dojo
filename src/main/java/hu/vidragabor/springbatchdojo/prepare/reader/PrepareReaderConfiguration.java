package hu.vidragabor.springbatchdojo.prepare.reader;

import hu.vidragabor.springbatchdojo.prepare.model.Prepare;
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
	
	private static final String SOURCE_COLUMNS = "id, first_name, last_name, age";
	private static final String SOURCE_TABLE_NAME = "prepare";
	private static final int DEFAULT_SIZE = 30;
	
	@Bean
	public JdbcPagingItemReader<Prepare> prepareReader(DataSource dataSource) {
		JdbcPagingItemReaderBuilder<Prepare> builder = new JdbcPagingItemReaderBuilder<Prepare>()
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
	
	private RowMapper<Prepare> getRowMapper() {
		return BeanPropertyRowMapper.newInstance(Prepare.class);
	}
	
	private String getSelectClause() {
		return SOURCE_COLUMNS;
	}
	
	private String getFromClause() {
		return SOURCE_TABLE_NAME;
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
