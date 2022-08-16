package com.paper.airline.repository;

import com.paper.airline.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInfoRepository extends JpaRepository<AppInfo, String> {
}
