package com.movieappV2.controller;

import com.movieappV2.dto.UserDto;
import com.movieappV2.dto.request.CreateUserRequest;
import com.movieappV2.dto.request.UpdateUserRequest;
import com.movieappV2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid CreateUserRequest request) {
        return new ResponseEntity<>(userService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-mail/{mail}")
    public ResponseEntity<UserDto> getByMail(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.getByMail(mail), HttpStatus.OK);
    }

    @DeleteMapping("/{mail}")
    public ResponseEntity<?> deleteByMail(@PathVariable("mail") String mail) {
        userService.deleteByMail(mail);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/deactivate/{mail}")
    public ResponseEntity<UserDto> deactivateUser(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.deactivateUser(mail), HttpStatus.OK);
    }

    @PutMapping("/activate/{mail}")
    public ResponseEntity<UserDto> activateUser(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.activateUser(mail), HttpStatus.OK);
    }

    @PutMapping("/update/{mail}")
    public ResponseEntity<UserDto> update(@PathVariable("mail") String mail, UpdateUserRequest request) {
        return new ResponseEntity<>(userService.update(mail, request), HttpStatus.OK);
    }
}
