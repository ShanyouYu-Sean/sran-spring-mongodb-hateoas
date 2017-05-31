package com.hongshen.service.lte;

import com.hongshen.exception.SranException;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
public interface Level3Service {

    List<Resource> getCellsAndStationsByNodeName(String userName,String tacName,String nodeName) throws SranException;
}
