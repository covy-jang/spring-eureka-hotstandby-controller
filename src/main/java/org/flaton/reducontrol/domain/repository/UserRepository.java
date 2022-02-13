package org.flaton.reducontrol.domain.repository;

import org.flaton.reducontrol.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
