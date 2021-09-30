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
public class RemoteDataSourceConfiguration {
	
	@Bean
	@ConfigurationProperties("remote.datasource")
	public DataSourceProperties remoteDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConditionalOnBean(name = "remoteDataSourceProperties")
	public HikariDataSource remoteDataSource(@Qualifier("remoteDataSourceProperties") DataSourceProperties remoteDataSourceProperties) {
		return remoteDataSourceProperties
				.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean
	@ConditionalOnBean(name = "remoteDataSource")
	public NamedParameterJdbcTemplate remoteNamedParameterJdbcTemplate(@Qualifier("remoteDataSource") DataSource remoteDataSource) {
		return new NamedParameterJdbcTemplate(remoteDataSource);
	}
	
}
