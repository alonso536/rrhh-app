package org.alonso.rrhhapp.services.auth;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.dto.CreateUserDTO;
import org.alonso.rrhhapp.models.entities.Role;
import org.alonso.rrhhapp.models.entities.UserEntity;
import org.alonso.rrhhapp.models.exceptions.EmailUniqueException;
import org.alonso.rrhhapp.models.exceptions.UserNotFoundException;
import org.alonso.rrhhapp.repositories.RoleRepository;
import org.alonso.rrhhapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> findAll(Pageable page) {
        return userRepository.findUsers(page);
    }

    @Override
    @Transactional
    public UserEntity save(CreateUserDTO createUserDTO) {
        List<Role> roles = new ArrayList<>();

        if (createUserDTO.getRoles() == null || createUserDTO.getRoles().isEmpty()) {
            roles.add(roleRepository.findByName("ROLE_USER"));
        } else {
            roles = createUserDTO.getRoles().stream()
                    .map((role) -> roleRepository.findByName(role))
                    .collect(Collectors.toList());
        }

        UserEntity user = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .roles(roles)
                .build();

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailUniqueException("The email entered is in use");
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("The employee entered is not found"));
    }

    @Override
    @Transactional
    public UserEntity delete(Long id) {
        UserEntity user = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("The employee entered is not found"));

        user.setActive(false);
        return userRepository.save(user);
    }
}
