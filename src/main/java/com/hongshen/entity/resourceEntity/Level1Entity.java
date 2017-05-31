package com.hongshen.entity.resourceEntity;


import com.fasterxml.jackson.annotation.*;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.nomalEntity.lte.Tac4g;
import com.hongshen.entity.nomalEntity.reference.Counter;
import com.hongshen.entity.nomalEntity.reference.Station;
import com.hongshen.entity.nomalEntity.wcdma.Rnc3g;
import com.hongshen.entity.nomalEntity.wcdma.Station3g;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@Data
@Relation(value = "tacStationOrRncStation",collectionRelation = "tacsStationsOrRncStations")
public class Level1Entity extends ResourceSupport {
    private String tacNameOrRncName = "";
    private String updateTime = "";
    private Integer tacStatusOrRncStatus = -1;
    private List<Counter> counters = new ArrayList<>();
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Station> stations = new ArrayList<>();


    public Level1Entity(Tac4g tac4g,List<Station4g> station4gs){
        super();
        this.tacNameOrRncName = tac4g.getTacName();
        this.updateTime = tac4g.getUpdateTime();
        this.tacStatusOrRncStatus = tac4g.getTacStatus();
        this.counters = tac4g.getCounters();
        for (Station4g station4g:station4gs){
            this.stations.add(new Station(station4g.getLongitude(),station4g.getLatitude()));
        }
    }
    public Level1Entity(Tac4g tac4g){
        super();
        this.tacNameOrRncName = tac4g.getTacName();
        this.updateTime = tac4g.getUpdateTime();
        this.tacStatusOrRncStatus = tac4g.getTacStatus();
        this.counters = tac4g.getCounters();
    }
    public Level1Entity(Rnc3g rnc3g, List<Station3g> station3gs){
        super();
        this.tacNameOrRncName = rnc3g.getRncName();
        this.updateTime = rnc3g.getUpdateTime();
        this.tacStatusOrRncStatus = rnc3g.getRncStatus();
        this.counters = rnc3g.getCounters();
        for (Station3g station3g:station3gs){
            this.stations.add(new Station(station3g.getLongitude(),station3g.getLatitude()));
        }
    }
    public Level1Entity(Rnc3g rnc3g){
        super();
        this.tacNameOrRncName = rnc3g.getRncName();
        this.updateTime = rnc3g.getUpdateTime();
        this.tacStatusOrRncStatus = rnc3g.getRncStatus();
        this.counters = rnc3g.getCounters();
    }
}

