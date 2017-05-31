package com.hongshen.repository.lte;

import com.hongshen.entity.nomalEntity.lte.Cell4g;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by a7289 on 2017/3/22 0022.
 */
@RepositoryRestResource(path = "lte-cells",collectionResourceRel = "cells",itemResourceRel = "cell")
public interface Cell4gRepository extends MongoRepository<Cell4g,String> {
    List<Cell4g> findByCellNameInOrderByCellStatusDesc(List<String> cellNames);
}
