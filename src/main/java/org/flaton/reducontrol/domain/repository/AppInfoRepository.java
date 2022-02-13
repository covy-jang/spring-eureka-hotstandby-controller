package org.flaton.reducontrol.domain.repository;

import org.flaton.reducontrol.domain.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInfoRepository extends JpaRepository<AppInfo, String> {
}
