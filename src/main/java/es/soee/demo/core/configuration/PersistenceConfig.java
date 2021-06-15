package es.soee.demo.core.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Value("${app.persistence.type}")
    private String type;

    @Value("${db.name}")
    private String databaseName;

    @Value("${db.h2.username}")
    private String dbH2Username;

    @Value("${db.h2.password}")
    private String dbH2Password;

    @Value("${db.sql.username}")
    private String dbSqlUsername;

    @Value("${db.sql.password}")
    private String dbSqlPassword;

    @Value("${db.sql.port}")
    private String dbSqlPort;

    @Bean
    public DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        if (type.equals("memory")) {
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setJdbcUrl(String.format("jdbc:h2:mem:%s", databaseName));
            dataSource.setUsername(dbH2Username);
            dataSource.setPassword(dbH2Password);
        } else if (type.equals("db")) {
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setJdbcUrl(String.format("jdbc:postgresql://localhost:%s/%s", dbSqlPort, databaseName));
            dataSource.setUsername(dbSqlUsername);
            dataSource.setPassword(dbSqlPassword);
        }

        return dataSource;
    }
}
