package com.buildweek.virtualrealityfunding.repository;

import com.buildweek.virtualrealityfunding.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
