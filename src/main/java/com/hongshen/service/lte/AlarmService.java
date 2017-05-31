package com.hongshen.service.lte;

import com.hongshen.entity.resourceEntity.AlarmEntity;
import com.hongshen.exception.SranException;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
public interface AlarmService {
    List<Resource> getNodeAlarmsByNodeName(String userName, String tacName, String nodeName)throws SranException;
}
