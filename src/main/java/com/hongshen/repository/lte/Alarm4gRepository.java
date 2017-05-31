package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.Alarm4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@RepositoryRestResource(path = "lte-alarms",collectionResourceRel = "alarms",itemResourceRel = "alarm")
public interface Alarm4gRepository extends MongoRepository<Alarm4g,String>{
    List<Alarm4g> findByNodeNameOrderByAlarmType(@Param("nodeName") String nodeName);
}
