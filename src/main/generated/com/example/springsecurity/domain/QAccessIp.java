package com.example.springsecurity.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccessIp is a Querydsl query type for AccessIp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccessIp extends EntityPathBase<AccessIp> {

    private static final long serialVersionUID = 443661861L;

    public static final QAccessIp accessIp = new QAccessIp("accessIp");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public QAccessIp(String variable) {
        super(AccessIp.class, forVariable(variable));
    }

    public QAccessIp(Path<? extends AccessIp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccessIp(PathMetadata metadata) {
        super(AccessIp.class, metadata);
    }

}

