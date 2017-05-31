package com.hongshen.service.lte.impl;

import com.hongshen.controller.lte.Level2Controller;
import com.hongshen.entity.nomalEntity.lte.Group4g;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.nomalEntity.lte.Tac4g;
import com.hongshen.entity.resourceEntity.Level1Entity;
import com.hongshen.exception.SranError;
import com.hongshen.exception.SranException;
import com.hongshen.repository.lte.Role4gRepository;
import com.hongshen.repository.lte.Station4gRepository;
import com.hongshen.repository.lte.Tac4gRepository;
import com.hongshen.service.lte.Level1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by a7289 on 2017/3/23 0023.
 */
@Service
public class Level1ServiceImpl implements Level1Service {

    @Autowired
    private Tac4gRepository tac4gRepository;
    @Autowired
    private Station4gRepository station4gRepository;
    @Autowired
    private Role4gRepository role4gRepository;
//    @Autowired
//    private UserRepository userRepository;


    @Override
    public List<Resource> getTacsAndStationsInRoleTacs(String roleTacs) {
        return null;
    }


    @Override
    public List getTacsAndStationsByUserName(String userName) throws SranException {
        List<Resource<Level1Entity>> level1Resources = new ArrayList<>();
        try {
//            //查询用户是否存在
//            User user = userRepository.findByUserName(userName);
//            if (user == null)
//                throw new SranException(SranError.USER_NOT_FOUND);
//            //获得用户4g角色名称
//            if (user.getRoleName4g() == null || user.getRoleName4g().equals(""))
//                throw new SranException(SranError._HAS_NO_ROLE_4G);
//            //得到用户4g角色的操作权限
//            Group4g group4G = role4gRepository.findByRoleName4g(user.getRoleName4g());

            //得到用户4g角色的操作权限
            Group4g group4G = role4gRepository.findByUserNamesIn(userName);
            if (group4G == null)
                throw new SranException(SranError.USER_HAS_NO_ROLE_4G);
            List<String> groupTacs = group4G.getGroupTacs();
            if (groupTacs == null || groupTacs.size() == 0)
                throw new SranException(SranError.ROLE_4G_HAS_NO_ROLE_TAC);
            List<Tac4g> tac4gs = new ArrayList<>();
            if (groupTacs.size() == 1 && groupTacs.get(0).equals("all")){
                tac4gs = tac4gRepository.findAll();
            }else {
                tac4gs = tac4gRepository.findByTacNameInOrderByTacStatusDesc(groupTacs);
            }

            if (tac4gs == null || tac4gs.size() == 0)
                throw new SranException(SranError.NO_TAC_4G_FOUND_UNDER_THIS_ROLE_4G);
            for (Tac4g tac4g :tac4gs){
                List<Station4g> station4gs = station4gRepository.findByTacName(tac4g.getTacName());
                Level1Entity level1Entity;
                if (station4gs!=null && station4gs.size()!=0) {
                    level1Entity = new Level1Entity(tac4g, station4gs);
                }else {
                    level1Entity = new Level1Entity(tac4g);
                }
                Resource<Level1Entity> level1Resource = new Resource<>(level1Entity);
                level1Resource.add(linkTo(Level2Controller.class,userName,tac4g.getTacName()).withRel("nextLink"));
                level1Resources.add(level1Resource);
            }
        }catch (SranException e){
            throw new SranException(e);
        } catch (Exception e){
            throw new SranException(SranError.UNKNOWN_ERROR,e);
        }
        return level1Resources;
    }
}
