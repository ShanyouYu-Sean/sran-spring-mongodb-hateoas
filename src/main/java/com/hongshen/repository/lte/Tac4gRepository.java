package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.Tac4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by a7289 on 2017/3/14 0014.
 */
@RepositoryRestResource(path = "lte-tacs",collectionResourceRel = "tacs",itemResourceRel = "tac")
public interface Tac4gRepository extends MongoRepository<Tac4g,String> {

    List<Tac4g> findByTacNameInOrderByTacStatusDesc(List<String> tacNames);

}
