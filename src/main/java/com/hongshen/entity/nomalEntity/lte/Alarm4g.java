package com.hongshen.entity.nomalEntity.lte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.core.Relation;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@Data
@Document
@Relation(value = "alarm",collectionRelation = "alarms")
public class Alarm4g {
    @Id
    @JsonIgnore
    private String id;
    private String nodeName;
    private String mo;
    private String alarmTime;
    private Integer alarmNameNum;
    private Integer alarmType;
}
