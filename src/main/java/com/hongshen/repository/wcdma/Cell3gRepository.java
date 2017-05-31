package com.hongshen.repository.wcdma;

import com.hongshen.entity.nomalEntity.lte.Cell4g;
import com.hongshen.entity.nomalEntity.wcdma.Cell3g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@RepositoryRestResource
public interface Cell3gRepository extends MongoRepository<Cell3g,String> {

    @RestResource(path = "listByName")
    List<Cell3g> findByCellName(@Param("name") String cellName);
}
