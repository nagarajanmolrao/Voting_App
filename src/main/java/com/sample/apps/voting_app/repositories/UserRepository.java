package com.sample.apps.voting_app.repositories;

import com.sample.apps.voting_app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
