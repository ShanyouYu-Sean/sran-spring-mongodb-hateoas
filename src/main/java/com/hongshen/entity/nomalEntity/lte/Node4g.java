package com.hongshen.entity.nomalEntity.lte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hongshen.entity.nomalEntity.reference.Counter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.core.Relation;

import java.util.List;

/**
 * Created by a7289 on 2017/3/14 0014.
 */
@Data
@Document
@Relation(value = "node",collectionRelation = "nodes")
public class Node4g implements java.io.Serializable{
    @Id
    @JsonIgnore
    private String id;
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String nodeName;
    private String updateTime;
    private Integer nodeStatus;
    private List<Counter> counters;
}
