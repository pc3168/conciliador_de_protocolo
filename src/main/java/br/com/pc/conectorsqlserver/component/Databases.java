package br.com.pc.conectorsqlserver.component;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public abstract class Databases extends LerArquivoSql{

    public List<Map<String, Object>> consulta(String query, JdbcTemplate jdbcTemplate){
        return jdbcTemplate.queryForList(query);
    }

}
