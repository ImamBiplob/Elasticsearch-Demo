package com.imambiplob.elasticsearchdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "place")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsPlace {

    @Id
    private long id;

    private String name;

    @GeoPointField
    private GeoPoint location;

}
