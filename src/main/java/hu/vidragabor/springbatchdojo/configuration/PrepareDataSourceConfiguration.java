package hu.vidragabor.springbatchdojo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class PrepareDataSourceConfiguration {
	
	@Bean
	@ConfigurationProperties("prepare.datasource")
	public DataSourceProperties prepareDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConditionalOnBean(name = "prepareDataSourceProperties")
	public HikariDataSource prepareDataSource(@Qualifier("prepareDataSourceProperties") DataSourceProperties prepareDataSourceProperties) {
		return prepareDataSourceProperties
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean
	@ConditionalOnBean(name = "prepareDataSource")
	public NamedParameterJdbcTemplate prepareNamedParameterJdbcTemplate(@Qualifier("prepareDataSource") DataSource prepareDataSource) {
		return new NamedParameterJdbcTemplate(prepareDataSource);
	}
	
}
