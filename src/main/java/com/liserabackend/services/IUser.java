package com.liserabackend.services;


import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;

import java.util.Optional;

public interface IUser {
    User saveUser(User user);
    Optional<User> updateEmail(String userId, String email) throws UseException;
    Optional<User> updateUsername(String userId, String username) throws UseException;
    Optional<User> updatePassword(String userId, String password) throws UseException;
    Optional<User> getAUserByUserName(String username) throws UseException;
    Optional<User> getAUserById(String userId) throws UseException;
    Optional<User> getAUserByEmail(String email) throws UseException;
}
