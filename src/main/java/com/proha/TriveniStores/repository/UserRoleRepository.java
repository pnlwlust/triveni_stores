package com.proha.TriveniStores.repository;

import com.proha.TriveniStores.domain.User;
import com.proha.TriveniStores.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository  extends CrudRepository<UserRole, String> {

    public UserRole findByUser(User user);
}
