package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.User;
import com.sample.apps.voting_app.service.interfaces.UserServiceInterface;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserServiceInterface {
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public Optional<User> readUser(String id) {
        return Optional.empty();
    }

    @Override
    public User updateUser(String id, User user) {
        return null;
    }

    @Override
    public Optional<User> deleteUser(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> login(String email, String password) {
        return Optional.empty();
    }

    @Override
    public User logout(User user) {
        return null;
    }

    @Override
    public User updateVoteStatus(String id) {
        return null;
    }
}
