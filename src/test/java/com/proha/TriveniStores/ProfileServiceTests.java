package com.proha.TriveniStores;

import com.proha.TriveniStores.domain.Profile;
import com.proha.TriveniStores.repository.ProfileRepository;
import com.proha.TriveniStores.service.ProfileService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
public class ProfileServiceTests {

    private final ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);
    private ProfileService profileService;

    @BeforeEach
    void initService(){
        profileService = new ProfileService(profileRepository);
    }

    @Test
    void profileSave(){
        Map<String, String> profileMap = new HashMap<>();
        profileMap.put("firstName","two");
        profileMap.put("lastName","two");
        profileMap.put("dateOfBirth","2020-20-90");
        profileMap.put("email","pnlwlust@gmail.com");
        Profile profile = new Profile();
        profile.setFirstName(profileMap.get("firstName"));

        assertNull(profile.getId());
        assert(profileService.save(profile));
        assertNotNull(profile.getId());

    }
}