package com.movieappV2.service;

import com.movieappV2.TestUtils;
import com.movieappV2.dto.UserDto;
import com.movieappV2.dto.converter.UserDtoConverter;
import com.movieappV2.dto.request.CreateUserRequest;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.User;
import com.movieappV2.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends TestUtils {

    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userDtoConverter = mock(UserDtoConverter.class);

        userService = new UserService(userRepository, userDtoConverter);
    }

    @Test
    public void testGetAll_itShouldReturnUserDtoList() {
        List<User> userList = generateUserList();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        when(userRepository.findAll()).thenReturn(userList);
        when(userDtoConverter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAll();

        assertEquals(result, userDtoList);
        verify(userRepository).findAll();
        verify(userDtoConverter).convert(userList);
    }

    @Test
    public void testGetByMail_itShouldReturnUserDto() {
        User user = new User(1L, "Test-Username", "Test-Password", "test@gmail.com",
                true, LocalDate.now(), LocalDate.now());
        UserDto userDto = new UserDto("Test-Username", "test@gmail.com", true);

        when(userRepository.findUserByMail("test@gmail.com")).thenReturn(Optional.of(user));
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getByMail("test@gmail.com");

        assertEquals(result, userDto);
        verify(userRepository).findUserByMail("test@gmail.com");
        verify(userDtoConverter).convert(user);
    }

    @Test
    public void testGetByMail_whenMailDoesNotExist_shouldThrowNotFoundException() {
        when(userRepository.findUserByMail("test@gmail.com")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserByMail("test@gmail.com"));

        verify(userRepository).findUserByMail("test@gmail.com");
    }

    @Test
    public void testGetUserByMail_itShouldReturnUser() {
        User user = new User(1L, "Test-Username", "Test-Password", "test@gmail.com",
                true, LocalDate.now(), LocalDate.now());

        when(userRepository.findUserByMail("test@gmail.com")).thenReturn(Optional.of(user));

        User result = userService.getUserByMail("test@gmail.com");

        assertEquals(result, user);
        verify(userRepository).findUserByMail("test@gmail.com");
    }

    @Test
    public void testGetUserByMail_whenMailDoesNotExist_shouldThrowNotFoundException() {
        when(userRepository.findUserByMail("test@gmail.com")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserByMail("test@gmail.com"));

        verify(userRepository).findUserByMail("test@gmail.com");
    }

    @Test
    public void testSave_itShouldReturnUserDto() {
        LocalDate createDate = LocalDate.now();

        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("Test-Username");
        request.setPassword("Test-Password");
        request.setMail("test@gmail.com");

        User savedUser = new User(1L, "Test-Username", "Test-Password", "test@gmail.com",
                true, createDate, LocalDate.now());
        User user = new User("Test-Username", "Test-Password", "test@gmail.com", true, createDate);
        UserDto userDto = new UserDto("Test-Username", "test@gmail.com", true);

        when(userRepository.save(user)).thenReturn(savedUser);
        when(userDtoConverter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.save(request);

        assertEquals(result, userDto);
        verify(userRepository).save(user);
        verify(userDtoConverter).convert(savedUser);
    }

    @Test
    public void testDeactivateUser_itShouldReturnUserDto() {
        User user = new User(1L, "Test-Username", "Test-Password", "test@gmail.com",
                true, LocalDate.now(), LocalDate.now());
        UserDto userDto = new UserDto("Test-Username", "test@gmail.com", true);

        when(userRepository.findUserByMail("test@gmail.com")).thenReturn(Optional.of(user));
        when(userService.deactivateUser("test@gmail.com")).thenReturn(userDto);
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.deactivateUser("test@gmail.com");

        assertEquals(result, userDto);
    }

}