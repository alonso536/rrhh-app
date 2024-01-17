package org.alonso.rrhhapp.services.auth;

import static org.alonso.rrhhapp.models.helpers.DataTestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.alonso.rrhhapp.models.entities.UserEntity;
import org.alonso.rrhhapp.models.exceptions.UserNotFoundException;
import org.alonso.rrhhapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFindAll() {
        when(userRepository.findUsers()).thenReturn(USERS_TEST);
        List<UserEntity> employees = userService.findAll();

        assertNotNull(employees);
        assertEquals(2, employees.size());

        verify(userRepository, times(1)).findUsers();
    }

    @Test
    void testFindById() {
        when(userRepository.findUserById(1L)).thenReturn(Optional.of(USERS_TEST.get(0)));
        UserEntity employee = userService.findById(1L);

        assertNotNull(employee);
        assertEquals(1L, employee.getId());

        verify(userRepository, times(1)).findUserById(1L);
    }

    @Test
    void testFindByIdNull() {
        when(userRepository.findUserById(5L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.findById(5L);
        });

        verify(userRepository, times(1)).findUserById(5L);
    }

    @Test
    void testDelete() {
        when(userRepository.findUserById(2L)).thenReturn(Optional.of(USERS_TEST.get(1)));
        when(userRepository.save(USERS_TEST.get(1))).thenReturn(USER_DELETED_TEST);

        UserEntity employee = userService.delete(2L);
        assertNotNull(employee);
        assertFalse(employee.getActive());

        verify(userRepository, times(1)).findUserById(2L);
        verify(userRepository, times(1)).save(USERS_TEST.get(1));
    }

    @Test
    void testDeleteNull() {
        when(userRepository.findUserById(5L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.delete(5L);
        });

        verify(userRepository, times(1)).findUserById(5L);
    }
}
