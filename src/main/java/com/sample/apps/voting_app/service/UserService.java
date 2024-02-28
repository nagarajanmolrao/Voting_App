package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.User;
import com.sample.apps.voting_app.service.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public Optional<User> readUser(Long id) {
        return Optional.empty();
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public Optional<User> deleteUser(Long id) {
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
    public User updateVoteStatus(Long id) {
        return null;
    }
}
