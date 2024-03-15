package com.sample.apps.voting_app.service.interfaces;

import com.sample.apps.voting_app.entities.User;

import java.util.Optional;

public interface UserServiceInterface {
    public User createUser(User user);

    public Optional<User> readUser(String id);

    public User updateUser(String id, User user);

    public Optional<User> deleteUser(String id);

    public Optional<User> login(String email, String password);

    public User logout(User user);

    public User updateVoteStatus(String id);
}
