package br.com.pc.conectorsqlserver.model;


import br.com.pc.conectorsqlserver.config.ChooseDB;

public class ModelBody {

    private ChooseDB choose;
    private String query;

    public ChooseDB getChoose() {
        return choose;
    }

    public void setChoose(ChooseDB choose) {
        this.choose = choose;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
