package com.hongshen.entity.resourceEntity;

import com.hongshen.entity.nomalEntity.lte.Alarm4g;
import com.hongshen.entity.nomalEntity.lte.AlarmName4g;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@Data
@Relation(value = "nodeAlarm",collectionRelation = "nodeAlarms")
public class AlarmEntity extends ResourceSupport {
    private String nodeName;
    private String mo;
    private String alarmTime;
    private Integer alarmNameNum;
    private String alarmNameDescription;
    private Integer alarmType;

    public AlarmEntity(Alarm4g alarm4g, String alarmNameDescription){
        super();
        this.nodeName = alarm4g.getNodeName();
        this.mo = alarm4g.getMo();
        this.alarmTime = alarm4g.getAlarmTime();
        this.alarmNameNum = alarm4g.getAlarmNameNum();
        this.alarmNameDescription = alarmNameDescription;
        this.alarmType = alarm4g.getAlarmType();
    }
    public AlarmEntity(){
        super();
    }
}

