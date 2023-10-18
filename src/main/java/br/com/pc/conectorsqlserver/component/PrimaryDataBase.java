package br.com.pc.conectorsqlserver.component;


import br.com.pc.conectorsqlserver.config.ChooseDB;
import br.com.pc.conectorsqlserver.config.TipoConexao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class PrimaryDataBase extends Databases{

    @Autowired
    @TipoConexao(ChooseDB.FIRST)
    private JdbcTemplate primaryJdbcTemplate;

    public List<Map<String, Object>> processar(InputStream file){
        String query = lerArquivoConsulta(file);
        return consulta(query, primaryJdbcTemplate);
    }

    public List<Map<String, Object>> processar(String query){
        return consulta(query, primaryJdbcTemplate);

    }

}
