package com.paper.airline.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.paper.airline.entity.AppInfo;
import com.paper.airline.vo.YesNo;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppInfo is a Querydsl query type for AppInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppInfo extends EntityPathBase<AppInfo> {

    private static final long serialVersionUID = -912782643L;

    public static final QAppInfo appInfo = new QAppInfo("appInfo");

    public final StringPath appName = createString("appName");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final StringPath modUserId = createString("modUserId");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath regUserId = createString("regUserId");

    public final EnumPath<YesNo> useYn = createEnum("useYn", YesNo.class);

    public QAppInfo(String variable) {
        super(AppInfo.class, forVariable(variable));
    }

    public QAppInfo(Path<? extends AppInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppInfo(PathMetadata metadata) {
        super(AppInfo.class, metadata);
    }

}

