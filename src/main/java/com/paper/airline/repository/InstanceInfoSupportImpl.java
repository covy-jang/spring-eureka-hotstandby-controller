package com.paper.airline.repository;

import com.paper.airline.dto.InstanceInfoDto;
import com.paper.airline.vo.Redundancy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.paper.airline.entity.InstanceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.paper.airline.domain.entity.QInstanceInfo.instanceInfo;

@Slf4j
@RequiredArgsConstructor
@Repository
public class InstanceInfoSupportImpl implements InstanceInfoSupport {
    private final JPAQueryFactory jpaQueryFactory;

    public List<InstanceInfo> findByAppName(String appName) {
        return jpaQueryFactory
                .selectFrom(instanceInfo)
                .where(instanceInfo.appName.eq(appName))
                .fetch();
    }

    public void setActive(InstanceInfoDto instanceInfoDto) {
        jpaQueryFactory
                .update(instanceInfo)
                .set(instanceInfo.redundancy, Redundancy.ACTIVE)
                .where(instanceInfo.appName.eq(instanceInfoDto.getAppName())
                        .and(instanceInfo.instanceId.eq(instanceInfoDto.getInstanceId())))
                .execute();
    }

    public void setStandbyExceptOne(InstanceInfoDto instanceInfoDto) {
        jpaQueryFactory
                .update(instanceInfo)
                .set(instanceInfo.redundancy, Redundancy.STANDBY)
                .where(instanceInfo.appName.eq(instanceInfoDto.getAppName())
                        .and(instanceInfo.instanceId.ne(instanceInfoDto.getInstanceId())))
                .execute();
    }

    public void setStandby(String appName) {
        jpaQueryFactory
                .update(instanceInfo)
                .set(instanceInfo.redundancy, Redundancy.STANDBY)
                .where(instanceInfo.appName.eq(appName))
                .execute();
    }
}
