package com.imambiplob.elasticsearchdemo.repository;

import com.imambiplob.elasticsearchdemo.entity.EsPlace;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsPlaceRepository extends ElasticsearchRepository<EsPlace, Long> {
    @Query("{\"bool\": {\"must\": {\"match_all\": {}},\"filter\": {\"geo_distance\": {\"distance\": \"?0\",\"location\": {\"lat\": ?1,\"lon\": ?2}}}}}")
    List<EsPlace> findNearbyLocation(String distance, double latitude, double longitude);

}
