package com.movieappV2.dto.converter;

import com.movieappV2.dto.UserDto;
import com.movieappV2.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User user) {
        return new UserDto(user.getUserName(),
                user.getMail(),
                user.isItActive());
    }

    public List<UserDto> convert(List<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toList());
    }

}
