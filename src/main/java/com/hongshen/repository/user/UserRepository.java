package com.hongshen.repository.user;

import com.hongshen.entity.nomalEntity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by a7289 on 2017/3/23 0023.
 */
@RepositoryRestResource(path = "users",collectionResourceRel = "users",itemResourceRel = "user")
public interface UserRepository extends MongoRepository<User,Long> {
    User findByUserName(String userName);
}
