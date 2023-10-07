package com.olasoj.socialapp.config.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
public class PersistenceConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("mainSqlDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setQueryTimeout(30);
        return jdbcTemplate;
    }

    @Primary
    @Bean(name = "mainSqlDataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "app.datasource.main")
    public HikariDataSource sqlDataSource() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/social_media?useEncoding=true&amp;characterEncoding=UTF-8");
        config.setUsername("olasoj");
        config.setPassword("P@ssw0rd");

        config.setAutoCommit(false);
        config.setConnectionTimeout(Duration.ofSeconds(5).toMillis());
        config.setMaximumPoolSize(Runtime.getRuntime().availableProcessors());
        config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");

        //
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        return new HikariDataSource(config);
    }

    @FlywayDataSource
    @Bean(initMethod = "migrate")
    public Flyway flyway(@Qualifier("mainSqlDataSource")
                         HikariDataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration") // this path is default
                .load();
    }

}
