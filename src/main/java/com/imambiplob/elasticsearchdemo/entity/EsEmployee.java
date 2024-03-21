package com.imambiplob.elasticsearchdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "employee")
@Setting(settingPath = "/es-config/elastic-analyzer.json")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsEmployee {

    @Id
    private long id;

    @Field(type = FieldType.Text, name = "name", analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String name;

    @Field(type = FieldType.Integer, name = "age")
    private int age;

    @Field(type = FieldType.Keyword, name = "department")
    private String department;

    @Field(type = FieldType.Integer, name = "salary")
    private int salary;

}
