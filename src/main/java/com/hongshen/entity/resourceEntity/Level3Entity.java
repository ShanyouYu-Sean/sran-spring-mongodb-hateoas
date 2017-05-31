package com.hongshen.entity.resourceEntity;

import com.hongshen.entity.nomalEntity.lte.Cell4g;
import com.hongshen.entity.nomalEntity.lte.Node4g;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.nomalEntity.reference.Counter;
import com.hongshen.entity.nomalEntity.wcdma.Cell3g;
import com.hongshen.entity.nomalEntity.wcdma.Node3g;
import com.hongshen.entity.nomalEntity.wcdma.Station3g;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a7289 on 2017/3/28 0028.
 */
@Data
@Relation(value = "cellStation",collectionRelation = "cellsStations")
public class Level3Entity extends ResourceSupport {
    private String cellName = "";
    private String updateTime = "";
    private Integer cellStatus = -1;
    private List<Counter> counters = new ArrayList<>();
    private String enbId = "";
    private String nodeName = "";
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

    public Level3Entity(Cell4g cell4g, Station4g station4g) {
        super();
        this.cellName = cell4g.getCellName();
        this.updateTime = cell4g.getUpdateTime();
        this.cellStatus = cell4g.getCellStatus();
        this.counters = cell4g.getCounters();
        this.enbId = station4g.getEnbId();
        this.nodeName = station4g.getNodeName();
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
    public Level3Entity(Cell3g cell3g, Station3g station3g) {
        super();
        this.cellName = cell3g.getCellName();
        this.updateTime = cell3g.getUpdateTime();
        this.cellStatus = cell3g.getCellStatus();
        this.counters = cell3g.getCounters();
        this.enbId = station3g.getEnbId();
        this.nodeName = station3g.getNodeName();
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
    public Level3Entity(String cellName,Station3g station3g) {
        super();
        this.cellName = cellName;
        this.enbId = station3g.getEnbId();
        this.nodeName = station3g.getNodeName();
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
    public Level3Entity(String cellName,Station4g station4g) {
        super();
        this.cellName = cellName;
        this.enbId = station4g.getEnbId();
        this.nodeName = station4g.getNodeName();
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
}
