package com.hongshen.exception;

import lombok.Data;
import org.springframework.hateoas.core.Relation;

/**
 * Created by a7289 on 2017/3/28 0028.
 */
@Data
@Relation(value = "error",collectionRelation = "errors")
public class SranExceptionEntity {
    private final String msg;
}
