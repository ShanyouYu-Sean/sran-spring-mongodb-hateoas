package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.Node4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/21 0021.
 */
@RepositoryRestResource(path = "lte-nodes",collectionResourceRel = "nodes",itemResourceRel = "node")
public interface Node4gRepository extends MongoRepository<Node4g,String> {

    Node4g findByNodeName(@Param("nodeName") String nodeName);
    List<Node4g> findByNodeNameInOrderByNodeStatusDesc(List<String> nodeNames);
}
