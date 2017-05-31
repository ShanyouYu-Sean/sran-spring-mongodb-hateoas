package com.hongshen.entity.nomalEntity.reference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by a7289 on 2017/3/14 0014.
 */
@Data
@Document
public class Counter {
    @Id
    @JsonIgnore
    private String id;
    private String chName;
    private String enName;
    private String dataType;
    private String dataUnit;
    private Double value;
    private Integer status;
    private Compare compare;
}
