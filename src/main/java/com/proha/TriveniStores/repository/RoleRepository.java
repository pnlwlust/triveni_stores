package com.proha.TriveniStores.repository;

import com.proha.TriveniStores.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {

    public Role findByAuthority(String authority);
}
