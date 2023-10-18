package br.com.pc.conectorsqlserver.config.conexao;

import br.com.pc.conectorsqlserver.config.ChooseDB;
import br.com.pc.conectorsqlserver.config.TipoConexao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "sqlserver-first")
    @ConfigurationProperties(prefix = "spring.datasource.primary-sqlserver")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @TipoConexao(ChooseDB.FIRST)
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("sqlserver-first") DataSource primaryDataSource){
        return new JdbcTemplate(primaryDataSource);
    }

    @Bean(name = "sqlserver-second")
    @ConfigurationProperties(prefix = "spring.datasource.secondary-sqlserver")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @TipoConexao(ChooseDB.SECOND)
    public JdbcTemplate secondaryDJdbcTemplate(@Qualifier("sqlserver-second") DataSource secondaryDataSource){
        return new JdbcTemplate(secondaryDataSource);
    }



}

