package com.paper.airline.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.paper.airline.entity.User;
import com.paper.airline.vo.YesNo;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -18462675L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserId = createString("modUserId");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath regUserId = createString("regUserId");

    public final StringPath userId = createString("userId");

    public final EnumPath<YesNo> useYn = createEnum("useYn", YesNo.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

