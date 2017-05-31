package com.hongshen.service.lte.impl;

import com.hongshen.controller.lte.AlarmController;
import com.hongshen.entity.nomalEntity.lte.Cell4g;
import com.hongshen.entity.nomalEntity.lte.Group4g;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.resourceEntity.Level3Entity;
import com.hongshen.exception.SranError;
import com.hongshen.exception.SranException;
import com.hongshen.repository.lte.Cell4gRepository;
import com.hongshen.repository.lte.Role4gRepository;
import com.hongshen.repository.lte.Station4gRepository;
import com.hongshen.service.lte.Level3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@Service
public class Level3ServiceImpl implements Level3Service {
    @Autowired
    private Cell4gRepository cell4gRepository;
    @Autowired
    private Station4gRepository station4gRepository;
    @Autowired
    private Role4gRepository role4gRepository;
//    @Autowired
//    private UserRepository userRepository;
    @Override
    public List getCellsAndStationsByNodeName(String userName, String tacName, String nodeName) throws SranException {
        List<Resource<Level3Entity>> level3Resources = new ArrayList<>();
        try {
//            User user = userRepository.findByUserName(userName);
//            if (user == null)
//                throw new SranException(SranError.USER_NOT_FOUND);
//            if (user.getRoleName4g() == null || user.getRoleName4g().equals(""))
//                throw new SranException(SranError.USER_HAS_NO_ROLE_4G);
//            Group4g group4G = role4gRepository.findByRoleName4g(user.getRoleName4g());
//            if (group4G == null)
//                throw new SranException(SranError.ROLE_4G_NOT_FOUND);
//            List<String> roleTacs = group4G.getRoleTacs();
//            if (roleTacs == null || roleTacs.size() == 0)
//                throw new SranException(SranError.ROLE_4G_HAS_NO_ROLE_TAC);
//            if (!roleTacs.contains(tacName))
//                throw new SranException(SranError.WRONG_TAC_UNDER_THIS_USER);

            // 得到用户4g角色的操作权限
            Group4g group4G = role4gRepository.findByUserNamesIn(userName);
            if (group4G == null)
                throw new SranException(SranError.USER_HAS_NO_ROLE_4G);
            List<String> groupTacs = group4G.getGroupTacs();
            if (groupTacs == null || groupTacs.size() == 0)
                throw new SranException(SranError.ROLE_4G_HAS_NO_ROLE_TAC);
            if (!(groupTacs.size() == 1 && groupTacs.get(0).equals("all")) && !groupTacs.contains(tacName))
                throw new SranException(SranError.WRONG_TAC_UNDER_THIS_USER);

            //获得一个node下的基站信息和cell列表
            Station4g station4g = station4gRepository.findByNodeName(nodeName);
            if (station4g == null)
                throw new SranException(SranError.NODE_NOT_FOUND);
            if (!station4g.getTacName().equals(tacName))
                throw new SranException(SranError.WRONG_NODE_UNDER_THIS_TAC);
            List<String> cellNames = station4g.getCellNames();
            if (cellNames == null || cellNames.size() == 0)
                throw new SranException(SranError.NO_CELL_FOUND_UNDER_THIS_NODE);

            List <Cell4g> cell4gs = cell4gRepository.findByCellNameInOrderByCellStatusDesc(cellNames);
            if (cell4gs != null && cell4gs.size() != 0 ){
                for (Cell4g cell4g : cell4gs){
                    Level3Entity level3Entity = new Level3Entity(cell4g,station4g);
                    cellNames.remove(cell4g.getCellName());
                    Resource<Level3Entity> level3Resource = new Resource<>(level3Entity);
                    level3Resource.add(linkTo(methodOn(AlarmController.class).getNodeAlarmsByNodeName(userName, tacName, nodeName)).withRel("nextLink"));
                    level3Resources.add(level3Resource);
                }
            }

            for (String cellName : cellNames){
                Level3Entity level3Entity = new Level3Entity(cellName,station4g);
                Resource<Level3Entity> level3Resource = new Resource<>(level3Entity);
                level3Resource.add(linkTo(methodOn(AlarmController.class).getNodeAlarmsByNodeName(userName, tacName, nodeName)).withRel("nextLink"));
                level3Resources.add(level3Resource);
            }

        }catch (SranException e){
            throw new SranException(e);
        } catch (Exception e){
            throw new SranException(SranError.UNKNOWN_ERROR,e);
        }
        return level3Resources;
    }
}
