package com.sample.apps.voting_app.service.interfaces;

import com.sample.apps.voting_app.entities.User;

import java.util.Optional;

public interface UserServiceInterface {
    public User createUser(User user);

    public Optional<User> readUser(Long id);

    public User updateUser(Long id, User user);

    public Optional<User> deleteUser(Long id);

    public Optional<User> login(String email, String password);

    public User logout(User user);

    public User updateVoteStatus(Long id);
}
