package org.flaton.reducontrol.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.flaton.reducontrol.domain.entity.InstanceInfo;
import org.flaton.reducontrol.domain.vo.Redundancy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.flaton.reducontrol.domain.entity.QInstanceInfo.instanceInfo;

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
