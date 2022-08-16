package com.paper.airline.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.paper.airline.entity.InstanceInfo;
import com.paper.airline.vo.Redundancy;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInstanceInfo is a Querydsl query type for InstanceInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInstanceInfo extends EntityPathBase<InstanceInfo> {

    private static final long serialVersionUID = 231273797L;

    public static final QInstanceInfo instanceInfo = new QInstanceInfo("instanceInfo");

    public final StringPath appName = createString("appName");

    public final StringPath hostName = createString("hostName");

    public final StringPath instanceId = createString("instanceId");

    public final StringPath ipAddr = createString("ipAddr");

    public final DateTimePath<java.time.LocalDateTime> lastUpdatedTimestamp = createDateTime("lastUpdatedTimestamp", java.time.LocalDateTime.class);

    public final EnumPath<Redundancy> redundancy = createEnum("redundancy", Redundancy.class);

    public final StringPath sid = createString("sid");

    public final EnumPath<com.netflix.appinfo.InstanceInfo.InstanceStatus> status = createEnum("status", com.netflix.appinfo.InstanceInfo.InstanceStatus.class);

    public QInstanceInfo(String variable) {
        super(InstanceInfo.class, forVariable(variable));
    }

    public QInstanceInfo(Path<? extends InstanceInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInstanceInfo(PathMetadata metadata) {
        super(InstanceInfo.class, metadata);
    }

}

