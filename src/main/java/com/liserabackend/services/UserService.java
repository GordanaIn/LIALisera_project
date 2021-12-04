package com.liserabackend.services;

import com.liserabackend.dto.CreateUser;
import com.liserabackend.dto.ModifyPasswordDTO;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.liserabackend.exceptions.UseExceptionType.USER_ALREADY_EXIST;
import static com.liserabackend.exceptions.UseExceptionType.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    /*
       User service for all the methods that have to do with creating the different users
       login
       adding role (creat student, create employee etc)
       changing password
     */


    //Users
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> updatePassword(String userId, String password) throws UseException {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setPassword(password);
        return Optional.of(saveUser(user));
    }
    public Optional<User> modifyPassword(ModifyPasswordDTO modifyPasswordDTO) throws UseException {
        final var user = userRepository.findAll().stream()
               .filter(u -> u.getPassword().equals(modifyPasswordDTO.getCurrentPassword()))
                .findAny().orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setPassword(modifyPasswordDTO.getNewPassword());
        userRepository.save(user);
        return Optional.of(saveUser(user));
    }
    // check if below methods should be used in the filter above?

    public Optional<User> getUserByEmail(String email) throws UseException {
        final var user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));
        return Optional.ofNullable(user);
    }

    public Optional<User> updateEmail(String userId, String email) throws UseException {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setUsername(email);
        return Optional.ofNullable(saveUser(user));
    }

    public Optional<User> createUser(CreateUser createUser) throws UseException {
        userRepository.findByUsername(createUser.getEmail()).orElseThrow( ()->new UseException(USER_ALREADY_EXIST));
        return  Optional.of(userRepository.save(new User(createUser.getEmail(), createUser.getPassword(), createUser.getRole())));
    }

}
