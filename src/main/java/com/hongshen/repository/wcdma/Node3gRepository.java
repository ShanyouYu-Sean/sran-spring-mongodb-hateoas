package com.hongshen.repository.wcdma;

import com.hongshen.entity.nomalEntity.lte.Node4g;
import com.hongshen.entity.nomalEntity.wcdma.Node3g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/21 0021.
 */
@RepositoryRestResource
public interface Node3gRepository extends MongoRepository<Node3g,String> {

    @RestResource(path = "listByName")
    List<Node3g> findByNodeName(@Param("name") String nodeName);
}
