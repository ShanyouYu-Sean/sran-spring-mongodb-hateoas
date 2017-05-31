package com.hongshen.entity.nomalEntity.lte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@Data
@Document
public class Group4g {
    @Id
    @JsonIgnore
    private String id;
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String groupName4g;
    private List<String> groupTacs;
    private List<String> userNames;
}
