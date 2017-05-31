package com.hongshen.entity.resourceEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hongshen.entity.nomalEntity.lte.Node4g;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.nomalEntity.reference.Counter;
import com.hongshen.entity.nomalEntity.wcdma.Node3g;
import com.hongshen.entity.nomalEntity.wcdma.Station3g;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by a7289 on 2017/3/23 0023.
 */
@Data
@Relation(value = "nodeStation",collectionRelation = "nodesStations")
public class Level2Entity extends ResourceSupport {
    private String nodeName = "";
    private String updateTime = "";
    private Integer nodeStatus = -1;
    private List<Counter> counters = new ArrayList<>();
    private String enbId = "";
    private String tacNameOrRncName = "";
    private String stationName = "";
    private String area = "";
    private String type = "";
    private String format = "";
    private String longitude = "";
    private String latitude = "";
    private String swversion = "";
    private String ip = "";
    private String mme = "";
    private String licenseUser = "";


    public Level2Entity(Node4g node4g, Station4g station4g) {
        super();
        this.nodeName = station4g.getNodeName();
        this.updateTime = node4g.getUpdateTime();
        this.nodeStatus = node4g.getNodeStatus();
        this.counters = node4g.getCounters();
        this.enbId = station4g.getEnbId();
        this.tacNameOrRncName = station4g.getTacName();
        this.stationName = station4g.getStationName();
        this.area = station4g.getArea();
        this.type = station4g.getType();
        this.format = station4g.getFormat();
        this.longitude = station4g.getLongitude();
        this.latitude = station4g.getLatitude();
        this.swversion = station4g.getSwversion();
        this.ip = station4g.getIp();
        this.mme = station4g.getMme();
        this.licenseUser = station4g.getLicenseUser();
    }

    public Level2Entity(Station4g station4g) {
        super();
        this.nodeName = station4g.getNodeName();
        this.enbId = station4g.getEnbId();
        this.tacNameOrRncName = station4g.getTacName();
        this.stationName = station4g.getStationName();
        this.area = station4g.getArea();
        this.type = station4g.getType();
        this.format = station4g.getFormat();
        this.longitude = station4g.getLongitude();
        this.latitude = station4g.getLatitude();
        this.swversion = station4g.getSwversion();
        this.ip = station4g.getIp();
        this.mme = station4g.getMme();
        this.licenseUser = station4g.getLicenseUser();
    }

    public Level2Entity(Node3g node3g, Station3g station3g) {
        super();
        this.nodeName = station3g.getNodeName();
        this.updateTime = node3g.getUpdateTime();
        this.nodeStatus = node3g.getNodeStatus();
        this.counters = node3g.getCounters();
        this.enbId = station3g.getEnbId();
        this.tacNameOrRncName = station3g.getRncName();
        this.stationName = station3g.getStationName();
        this.area = station3g.getArea();
        this.type = station3g.getType();
        this.format = station3g.getFormat();
        this.longitude = station3g.getLongitude();
        this.latitude = station3g.getLatitude();
        this.swversion = station3g.getSwversion();
        this.ip = station3g.getIp();
        this.mme = station3g.getMme();
        this.licenseUser = station3g.getLicenseUser();
    }
    public Level2Entity(Station3g station3g) {
        super();
        this.nodeName = station3g.getNodeName();
        this.enbId = station3g.getEnbId();
        this.tacNameOrRncName = station3g.getRncName();
        this.stationName = station3g.getStationName();
        this.area = station3g.getArea();
        this.type = station3g.getType();
        this.format = station3g.getFormat();
        this.longitude = station3g.getLongitude();
        this.latitude = station3g.getLatitude();
        this.swversion = station3g.getSwversion();
        this.ip = station3g.getIp();
        this.mme = station3g.getMme();
        this.licenseUser = station3g.getLicenseUser();
    }
}
