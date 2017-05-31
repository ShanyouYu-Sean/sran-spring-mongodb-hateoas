package com.hongshen.service.lte.impl;

import com.hongshen.controller.lte.Level3Controller;
import com.hongshen.entity.nomalEntity.lte.Group4g;
import com.hongshen.entity.nomalEntity.lte.Node4g;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.resourceEntity.Level2Entity;
import com.hongshen.exception.SranError;
import com.hongshen.exception.SranException;
import com.hongshen.repository.lte.Node4gRepository;
import com.hongshen.repository.lte.Role4gRepository;
import com.hongshen.repository.lte.Station4gRepository;
import com.hongshen.service.lte.Level2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@Service
public class Level2ServiceImpl implements Level2Service{

    @Autowired
    private Node4gRepository node4gRepository;
    @Autowired
    private Station4gRepository station4gRepository;
    @Autowired
    private Role4gRepository role4gRepository;
//    @Autowired
//    private UserRepository userRepository;
    @Override
    public List getNodesAndStationsByTacName(String userName,String tacName) throws SranException{

        List<Resource<Level2Entity>> level2Resources = new ArrayList<>();

        try {
//            User user = userRepository.findByUserName(userName);
//            if (user == null)
//                throw new SranException(SranError.USER_NOT_FOUND);
//            if (user.getRoleName4g() == null || user.getRoleName4g().equals(""))
//                throw new SranException(SranError.USER_HAS_NO_ROLE_4G);
//            Group4g group4G = role4gRepository.findByRoleName4g(user.getRoleName4g());

            // 得到用户4g角色的操作权限
            Group4g group4G = role4gRepository.findByUserNamesIn(userName);
            if (group4G == null)
                throw new SranException(SranError.USER_HAS_NO_ROLE_4G);
            List<String> groupTacs = group4G.getGroupTacs();
            if (groupTacs == null || groupTacs.size() == 0)
                throw new SranException(SranError.ROLE_4G_HAS_NO_ROLE_TAC);
            if (!(groupTacs.size() == 1 && groupTacs.get(0).equals("all")) && !groupTacs.contains(tacName))
                throw new SranException(SranError.WRONG_TAC_UNDER_THIS_USER);

            //获得一个tac下的所有station
            List<Station4g> station4gs = station4gRepository.findByTacName(tacName);
            if (station4gs == null || station4gs.size() == 0)
                throw new SranException(SranError.NO_NODE_FOUND_UNDER_THIS_TAC);
            List<String> nodeNames = new ArrayList<>();
            Map<String,Station4g> station4gMap = new HashMap<>();
            for (Station4g station4g : station4gs) {
                //获得跟station相关联的nodes
                nodeNames.add(station4g.getNodeName());
                station4gMap.put(station4g.getNodeName(),station4g);
            }


            List<Node4g> node4gs = node4gRepository.findByNodeNameInOrderByNodeStatusDesc(nodeNames);
            if (node4gs != null || node4gs.size() != 0) {
                for (Node4g node4g : node4gs) {
                    Level2Entity level2Entity = new Level2Entity(node4g, station4gMap.get(node4g.getNodeName()));
                    nodeNames.remove(node4g.getNodeName());
                    Resource<Level2Entity> Level2Resource = new Resource<>(level2Entity);
                    Level2Resource.add(linkTo(Level3Controller.class,userName,tacName,node4g.getNodeName()).withRel("nextLink"));
                    level2Resources.add(Level2Resource);
                }
            }

            for (String nodeName : nodeNames){
                Level2Entity level2Entity = new Level2Entity(station4gMap.get(nodeName));
                Resource<Level2Entity> Level2Resource = new Resource<>(level2Entity);
                Level2Resource.add(linkTo(Level3Controller.class,userName,tacName,nodeName).withRel("nextLink"));
                level2Resources.add(Level2Resource);
            }
        }catch (SranException e){
            throw new SranException(e);
        } catch (Exception e){
            throw new SranException(SranError.UNKNOWN_ERROR,e);
        }
        return level2Resources;
    }
}
