package com.hongshen.entity.nomalEntity.lte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hongshen.entity.nomalEntity.reference.Station;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.core.Relation;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@Data
@Document
@Relation(value = "station",collectionRelation = "stations")
public class Station4g implements java.io.Serializable{
    @Id
    @JsonIgnore
    private String id;
    private String enbId;
    private String tacName;
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String nodeName;
    private List<String> cellNames;
    private String stationName;
    private String area;
    private String type;
    private String format;
    private String longitude;
    private String latitude;
    private String swversion;
    private String ip;
    private String mme;
    private String licenseUser;

}
