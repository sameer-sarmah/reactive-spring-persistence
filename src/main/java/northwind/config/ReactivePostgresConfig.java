package northwind.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.r2dbc.function.TransactionalDatabaseClient;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@PropertySource("classpath:postgres.properties")
public class ReactivePostgresConfig {
	@Value( "${postgres.url}" )
	private String jdbcUrl;
	
	@Value( "${postgres.driverClassName}" )
	private String driver;
	
	@Value( "${postgres.username}" )
	private String username;
	
	@Value( "${postgres.password}" )
	private String password;
	
	@Value( "${postgres.host}" )
	private String host;
	
	@Value( "${postgres.port}" )
	private int port;
	
	@Value( "${postgres.schema}" )
	private String schema;
	
	@Value( "${postgres.database}" )
	private String database;
	
	   @Bean
	   TransactionalDatabaseClient databaseClient(ConnectionFactory factory) {
	      return TransactionalDatabaseClient.builder()
	         .connectionFactory(factory)
	         .build();
	   }

	   @Bean
	   PostgresqlConnectionFactory connectionFactory() {

	      PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
	         .host(host)
	         .port(port)
	         .database(database)
	         .schema(schema)
	         .username(username)
	         .password(password)
	         .build();

	      return new PostgresqlConnectionFactory(config);
	   }
	
}
