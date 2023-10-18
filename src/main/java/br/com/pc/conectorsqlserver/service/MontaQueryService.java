package br.com.pc.conectorsqlserver.service;


import br.com.pc.conectorsqlserver.component.Databases;
import br.com.pc.conectorsqlserver.component.PrimaryDataBase;
import br.com.pc.conectorsqlserver.component.SecundaryDataBase;
import br.com.pc.conectorsqlserver.config.ChooseDB;
import br.com.pc.conectorsqlserver.model.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class MontaQueryService extends Databases {

    @Autowired
    private PrimaryDataBase primaryDataBase;
    @Autowired
    private SecundaryDataBase secundaryDataBase;

    public QueryResult listDeMaps(ChooseDB chooseDB , InputStream file){
        List<Map<String, Object>> lista;
        if (ChooseDB.SECOND.equals(chooseDB)){
            lista = secundaryDataBase.processar(file);
        }else{
            lista = primaryDataBase.processar(file);
        }
        return new QueryResult(lista);
    }

    public QueryResult listDeMaps(ChooseDB chooseDB , String query){
        List<Map<String, Object>> lista;
        if (ChooseDB.SECOND.equals(chooseDB)){
            lista = secundaryDataBase.processar(query);
        }else{
            lista = primaryDataBase.processar(query);
        }
        return new QueryResult(lista);
    }

}
