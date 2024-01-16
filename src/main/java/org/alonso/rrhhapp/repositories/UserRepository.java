package org.alonso.rrhhapp.repositories;

import java.util.List;
import java.util.Optional;

import org.alonso.rrhhapp.models.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.active = true")
    List<UserEntity> findUsers();

    @Query("SELECT u FROM UserEntity u WHERE u.active = true")
    Page<UserEntity> findUsers(Pageable page);

    @Query("SELECT u FROM UserEntity u WHERE u.active = true AND u.id = :id")
    Optional<UserEntity> findUserById(@Param("id") Long id);

    @Query("SELECT u FROM UserEntity u WHERE u.active = true AND u.username = :username")
    Optional<UserEntity> findUserByUsername(@Param("username") String username);
}
