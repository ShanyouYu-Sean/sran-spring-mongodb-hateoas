package com.hongshen.repository.wcdma;

import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.nomalEntity.wcdma.Station3g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@RepositoryRestResource
public interface Station3gRepository extends MongoRepository<Station3g,String>{
}
