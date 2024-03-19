package com.imambiplob.elasticsearchdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "employee", createIndex = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsEmployee {

    @Id
    private long id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Integer, name = "age")
    private int age;

    @Field(type = FieldType.Keyword, name = "department")
    private String department;

    @Field(type = FieldType.Integer, name = "salary")
    private int salary;

}
