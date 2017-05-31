package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.AlarmName4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@RepositoryRestResource(path = "lte-alarmNames",collectionResourceRel = "alarmNames",itemResourceRel = "alarmName")
public interface AlarmName4gRepository extends MongoRepository<AlarmName4g,String> {
    AlarmName4g findByAlarmNameNum(@Param("alarmNameNum") Integer alarmNameNum);
}
