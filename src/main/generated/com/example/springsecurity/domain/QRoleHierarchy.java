package com.example.springsecurity.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleHierarchy is a Querydsl query type for RoleHierarchy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoleHierarchy extends EntityPathBase<RoleHierarchy> {

    private static final long serialVersionUID = -2029794779L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleHierarchy roleHierarchy1 = new QRoleHierarchy("roleHierarchy1");

    public final StringPath childName = createString("childName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRoleHierarchy parentName;

    public final SetPath<RoleHierarchy, QRoleHierarchy> roleHierarchy = this.<RoleHierarchy, QRoleHierarchy>createSet("roleHierarchy", RoleHierarchy.class, QRoleHierarchy.class, PathInits.DIRECT2);

    public QRoleHierarchy(String variable) {
        this(RoleHierarchy.class, forVariable(variable), INITS);
    }

    public QRoleHierarchy(Path<? extends RoleHierarchy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoleHierarchy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoleHierarchy(PathMetadata metadata, PathInits inits) {
        this(RoleHierarchy.class, metadata, inits);
    }

    public QRoleHierarchy(Class<? extends RoleHierarchy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentName = inits.isInitialized("parentName") ? new QRoleHierarchy(forProperty("parentName"), inits.get("parentName")) : null;
    }

}

