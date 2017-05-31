package com.hongshen.service.lte;

import com.hongshen.exception.SranException;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
public interface Level2Service {
    List<Resource> getNodesAndStationsByTacName(String userName,String tacName) throws SranException;
}
