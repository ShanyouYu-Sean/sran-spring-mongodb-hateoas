package com.hongshen.repository.wcdma;

import com.hongshen.entity.nomalEntity.wcdma.Rnc3g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/14 0014.
 */
@RepositoryRestResource
public interface Rnc3gRepository extends MongoRepository<Rnc3g,String> {
    @RestResource(path = "listByName")
    List<Rnc3g> findByRncName(@Param("name") String rncName);
}
