package br.com.pc.conectorsqlserver.component;


import br.com.pc.conectorsqlserver.util.FileStringUtil;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class LerArquivoSql {

    public String lerArquivoConsulta(File file, String[] args){
        return concatenarQuery(FileStringUtil.FileToString(file), args);
    }

    public String lerArquivoConsulta(InputStream file, String[] args){
        return concatenarQuery(FileStringUtil.FileToString(file), args);
    }

    public String lerArquivoConsulta(File file, String arg){
        return lerArquivoConsulta(file, new String[] {arg});
    }

    public String lerArquivoConsulta(InputStream file, String arg){
        return lerArquivoConsulta(file, new String[] {arg});
    }

    public String lerArquivoConsulta(File file){
        return lerArquivoConsulta(file, "");
    }

    public String lerArquivoConsulta(InputStream file){
        return lerArquivoConsulta(file, "");
    }



    public String montaCondicaoPeriodo(String coluna, LocalDate inicio, LocalDate fim) {
        String condicaoPeriodo = String.format(" AND %s ", coluna) + periodoBetween(inicio, fim);
        return condicaoPeriodo;
    }

    public String periodoBetween(LocalDate inicio, LocalDate fim) {
        String dataInicial = formataLocalDate(inicio);
        String dataFinal = formataLocalDate(fim);
        String condicaoPeriodo = String.format(" BETWEEN '%s' AND '%s'", dataInicial, dataFinal);
        return condicaoPeriodo;
    }

    /**
     * pattern padrão yyyyMMdd
     * @param data
     * @return
     */
    public String formataLocalDate(LocalDate data){
        return formataLocalDate(data, "yyyyMMdd");
    }

    public String formataLocalDate(LocalDate data, String pattern){
        return data.format(DateTimeFormatter.ofPattern(pattern));
    }

    private String concatenarQuery(String query, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(query);
        for (String arg: args) {
            sb.append(" ");
            sb.append(arg);
            sb.append(" ");
        }
        return sb.toString();
    }

}
