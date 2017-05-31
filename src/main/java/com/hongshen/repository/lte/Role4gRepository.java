package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.Group4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by a7289 on 2017/3/23 0023.
 */
@RepositoryRestResource(path = "lte-groups",collectionResourceRel = "groups",itemResourceRel = "group")
public interface Role4gRepository extends MongoRepository<Group4g,String> {
    @RestResource(path = "/groups")
    Group4g findByGroupName4g(@Param("roleName") String roleName4g);
    @RestResource(path="/group4gByUserName")
    Group4g findByUserNamesIn(@Param("userName") String userName);
}
