package com.yazukov.proj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "item")
public class ElasticItem {
    @Id
    private Long id;
    private String tags;
    private Map<String, String> fields;
    private String name;
}
