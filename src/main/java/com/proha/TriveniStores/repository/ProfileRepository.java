package com.proha.TriveniStores.repository;

import com.proha.TriveniStores.domain.Profile;
import com.proha.TriveniStores.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, String> {

    public Profile findByFirstName(String firstName);

    public Profile findByUser(User user);
}
