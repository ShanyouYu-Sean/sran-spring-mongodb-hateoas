package com.hongshen.service.lte;

import com.hongshen.exception.SranException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */

public interface Level1Service {
    List<Resource> getTacsAndStationsInRoleTacs(String roleTacs);
    List<Resource> getTacsAndStationsByUserName(String userName) throws SranException;

}
