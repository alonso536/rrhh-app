package org.alonso.rrhhapp.services.auth;

import java.util.List;

import org.alonso.rrhhapp.models.dto.CreateUserDTO;
import org.alonso.rrhhapp.models.dto.UserDTO;
import org.alonso.rrhhapp.models.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    List<UserEntity> findAll();

    Page<UserEntity> findAll(Pageable page);

    UserEntity save(CreateUserDTO createUserDTO);

    UserDTO checkAuth(String token);

    UserEntity findById(Long id);

    UserEntity delete(Long id);
}
