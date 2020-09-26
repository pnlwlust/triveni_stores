package com.proha.TriveniStores.repository;

import com.proha.TriveniStores.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, String> {

    public User findByUsername(String username);
}
