package com.imambiplob.elasticsearchdemo.repository;

import com.imambiplob.elasticsearchdemo.entity.EsEmployee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsEmployeeRepository extends ElasticsearchRepository<EsEmployee, Long> {
}
