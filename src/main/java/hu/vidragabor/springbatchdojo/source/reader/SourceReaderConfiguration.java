package hu.vidragabor.springbatchdojo.source.reader;

import hu.vidragabor.springbatchdojo.source.model.Source;
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
public class SourceReaderConfiguration {
	
	private static final String SOURCE_COLUMNS = "id, first_name, last_name, age";
	private static final String SOURCE_TABLE_NAME = "source";
	private static final int DEFAULT_SIZE = 30;
	
	@Bean
	public JdbcPagingItemReader<Source> reader(DataSource dataSource) {
		JdbcPagingItemReaderBuilder<Source> builder = new JdbcPagingItemReaderBuilder<Source>()
				.name("sourceReader")
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
	
	private RowMapper<Source> getRowMapper() {
		return BeanPropertyRowMapper.newInstance(Source.class);
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
