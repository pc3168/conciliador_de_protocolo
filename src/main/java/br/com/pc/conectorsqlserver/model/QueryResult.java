package br.com.pc.conectorsqlserver.model;

import java.util.List;
import java.util.Map;

public class QueryResult {
    private List<Map<String, Object>> results;

    public QueryResult(List<Map<String, Object>> results) {
        this.results = results;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "results=" + results +
                '}';
    }
}
