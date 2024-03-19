package com.imambiplob.elasticsearchdemo.repository;

import com.imambiplob.elasticsearchdemo.entity.EsEmployee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsEmployeeRepository extends ElasticsearchRepository<EsEmployee, Long> {
    List<EsEmployee> findByAgeBetween(int from, int to);
}
