package com.example.springsecurity.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResources is a Querydsl query type for Resources
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResources extends EntityPathBase<Resources> {

    private static final long serialVersionUID = 1000604907L;

    public static final QResources resources = new QResources("resources");

    public final StringPath httpMethod = createString("httpMethod");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> orderNum = createNumber("orderNum", Integer.class);

    public final StringPath resourceName = createString("resourceName");

    public final StringPath resourceType = createString("resourceType");

    public final SetPath<Role, QRole> roleSet = this.<Role, QRole>createSet("roleSet", Role.class, QRole.class, PathInits.DIRECT2);

    public QResources(String variable) {
        super(Resources.class, forVariable(variable));
    }

    public QResources(Path<? extends Resources> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResources(PathMetadata metadata) {
        super(Resources.class, metadata);
    }

}

