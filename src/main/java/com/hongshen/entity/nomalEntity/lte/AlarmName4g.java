package com.hongshen.entity.nomalEntity.lte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.core.Relation;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@Data
@Document
@Relation(value = "alarmName",collectionRelation = "alarmNames")
public class AlarmName4g {
    @Id
    @JsonIgnore
    private String id;
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private Integer alarmNameNum;
    private String alarmNameDescription;
}
