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
public class Compare {
    @Id
    @JsonIgnore
    private String id;
    private String compareType;
    private String threshold1;
    private String threshold2;
    private String threshold3;
}
