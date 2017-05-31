package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.Station4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@RepositoryRestResource(path = "lte-stations",collectionResourceRel = "stations",itemResourceRel = "station")
public interface Station4gRepository extends MongoRepository<Station4g,String>{
    /**
     * 获得一个tac下的stations
     * @param tacName
     * @return
     */
    @RestResource(path = "stations")
    List<Station4g> findByTacName(@Param("tacName") String tacName);
    Station4g findByNodeName(@Param("nodeName") String nodeName);
}
