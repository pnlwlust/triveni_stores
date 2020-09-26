package com.proha.TriveniStores.repository;

import com.proha.TriveniStores.domain.RegistrationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTokenRepository extends CrudRepository<RegistrationToken, Long> {

    public RegistrationToken findByToken(String token);
}
