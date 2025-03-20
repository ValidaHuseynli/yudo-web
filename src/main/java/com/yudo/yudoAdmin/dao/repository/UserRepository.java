package com.yudo.yudoAdmin.dao.repository;

import com.yudo.yudoAdmin.dao.entity.Store;
import com.yudo.yudoAdmin.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
