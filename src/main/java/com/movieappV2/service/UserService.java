package com.movieappV2.service;

import com.movieappV2.dto.UserDto;
import com.movieappV2.dto.converter.UserDtoConverter;
import com.movieappV2.dto.request.CreateUserRequest;
import com.movieappV2.dto.request.UpdateUserRequest;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.User;
import com.movieappV2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserDto save(CreateUserRequest request) {
        User user = new User(request.getUserName(),
                request.getPassword(),
                request.getMail(),
                true);

        return userDtoConverter.convert(userRepository.save(user));
    }

    public List<UserDto> getAll() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getByMail(String mail) {
        User user = userRepository.findUserByMail(mail)
                .orElseThrow(() -> new NotFoundException("user not found, user mail: " + mail));

        return userDtoConverter.convert(user);
    }

    public void deleteByMail(String mail) {
        User user = userRepository.findUserByMail(mail)
                .orElseThrow(() -> new NotFoundException("user not found, user mail: " + mail));

        userRepository.deleteById(user.getId());
    }

    public UserDto deactivateUser(String mail) {
        User user = getUserByMail(mail);
        user.setItActive(false);

        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto activateUser(String mail) {
        User user = getUserByMail(mail);
        user.setItActive(true);

        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto update(String mail, UpdateUserRequest request) {
        User user = getUserByMail(mail);

        user.setPassword(request.getPassword());

        return userDtoConverter.convert(userRepository.save(user));
    }

    protected User getUserByMail(String mail) {
        return userRepository.findUserByMail(mail).orElseThrow(
                () -> new NotFoundException("user not found, user mail: " + mail));
    }

}
