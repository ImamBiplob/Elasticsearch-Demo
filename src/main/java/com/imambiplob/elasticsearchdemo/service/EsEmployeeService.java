package com.imambiplob.elasticsearchdemo.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.imambiplob.elasticsearchdemo.entity.EsEmployee;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsEmployeeService {
    private final ElasticsearchOperations esOps;

    private final ElasticsearchClient esClient;

    public EsEmployeeService(ElasticsearchOperations esOps, ElasticsearchClient esClient) {
        this.esOps = esOps;
        this.esClient = esClient;
    }

    public void saveEmployee(EsEmployee employee) {
        esOps.save(employee);
    }

    public List<String> searchEmployeeByName(String name) throws IOException {
        SearchResponse<EsEmployee> searchResponse = esClient.search(s -> s
                .index("employee")
                .query(q -> q
                        .match(t -> t
                                .field("name")
                                .query(name))), EsEmployee.class);

        List<Hit<EsEmployee>> hits = searchResponse.hits().hits();

        List<String> names = new ArrayList<>();
        for (Hit<EsEmployee> hit : hits) {
            if (hit.source() != null)
                names.add(hit.source().getName());
        }

        return names;
    }

    public List<EsEmployee> searchEmployeeWithSalaryBetween(int startingSalary, int endingSalary) {
        Criteria criteria = new Criteria("salary")
                .greaterThan(startingSalary)
                .lessThan(endingSalary);
        Query query = new CriteriaQuery(criteria);

        SearchHits<EsEmployee> searchHits = esOps.search(query, EsEmployee.class);

        return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }

    public List<EsEmployee> searchStringQuery(String name) {
        Query query = new StringQuery("{ \"match\": { \"name\": { \"query\": \"" + name + "\" } } } ");

        SearchHits<EsEmployee> searchHits = esOps
                .search(query, EsEmployee.class);

        return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }
}
