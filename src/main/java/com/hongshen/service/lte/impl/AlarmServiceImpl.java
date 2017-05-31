package com.hongshen.service.lte.impl;

import com.hongshen.entity.nomalEntity.lte.Alarm4g;
import com.hongshen.entity.nomalEntity.lte.AlarmName4g;
import com.hongshen.entity.nomalEntity.lte.Group4g;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.resourceEntity.AlarmEntity;
import com.hongshen.exception.SranError;
import com.hongshen.exception.SranException;
import com.hongshen.repository.lte.Alarm4gRepository;
import com.hongshen.repository.lte.AlarmName4gRepository;
import com.hongshen.repository.lte.Role4gRepository;
import com.hongshen.repository.lte.Station4gRepository;
import com.hongshen.service.lte.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@Service
public class AlarmServiceImpl implements AlarmService {
    @Autowired
    private Alarm4gRepository alarm4gRepository;
    @Autowired
    private AlarmName4gRepository alarmName4gRepository;
    @Autowired
    private Station4gRepository station4gRepository;
    @Autowired
    private Role4gRepository role4gRepository;
//    @Autowired
//    private static UserRepository userRepository;


    @Override
    public List getNodeAlarmsByNodeName(String userName, String tacName, String nodeName) throws SranException {
        List<Resource> alarmResources = new ArrayList<>();
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

            List<Alarm4g> alarm4gs = alarm4gRepository.findByNodeNameOrderByAlarmType(nodeName);
            AlarmEntity alarmEntity = null;
//            没有告警的情况，考虑两种处理方法，一是请求出错通过返回码判断，而是请求正确前台判断
            if (alarm4gs != null || alarm4gs.size()!=0)
                throw new SranException(SranError.NO_ALARM_FOUND_UNDER_THIS_NODE);
            if (alarm4gs != null && alarm4gs.size()!=0){
                for (Alarm4g alarm4g : alarm4gs){
                    if (alarm4g.getAlarmNameNum() != null){
                        AlarmName4g alarmName4g = alarmName4gRepository.findByAlarmNameNum(alarm4g.getAlarmNameNum());
                        if (alarmName4g != null){
                            alarmEntity = new AlarmEntity(alarm4g,alarmName4g.getAlarmNameDescription());
                        }else {
                            alarmEntity = new AlarmEntity(alarm4g,"");
                        }
                    }else {
                        alarmEntity = new AlarmEntity(alarm4g,"");
                    }
                    Resource<AlarmEntity> alarmRsource = new Resource<>(alarmEntity);
                    alarmResources.add(alarmRsource);
                }
            }
//            else {
//                alarmEntity = new AlarmEntity();
//                Resource<AlarmEntity> alarmRsource = new Resource<>(alarmEntity);
//                alarmResources.add(alarmRsource);
//            }
        }catch (SranException e){
            throw new SranException(e);
        } catch (Exception e){
            throw new SranException(SranError.UNKNOWN_ERROR,e);
        }

        return alarmResources;
    }
}
