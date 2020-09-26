package com.proha.TriveniStores.service;

import com.proha.TriveniStores.domain.Profile;
import com.proha.TriveniStores.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public boolean save(Profile profile){

        if(profileRepository.save(profile) != null){
            return true;
        }
        return false;
    }
}
