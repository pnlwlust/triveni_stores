package com.proha.TriveniStores;

import com.proha.TriveniStores.domain.Role;
import com.proha.TriveniStores.domain.Roles;
import com.proha.TriveniStores.domain.User;
import com.proha.TriveniStores.domain.UserRole;
import com.proha.TriveniStores.repository.RoleRepository;
import com.proha.TriveniStores.repository.UserRepository;
import com.proha.TriveniStores.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

//@ComponentScan({"com.proha.TriveniStores.config"})
public class DataInit {

    public static void initUsers(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder){

        String adminUserName = "pnlwlust";
        String adminPassword = "pnlwlust@123";
        Role adminRole = roleRepository.findByAuthority(Roles.ADMIN.type);
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setAuthority(Roles.ADMIN.type);
            roleRepository.save(adminRole);
        }

        Role userRole = roleRepository.findByAuthority(Roles.USER.type);
        if (userRole == null) {
            userRole = new Role();
            userRole.setAuthority(Roles.USER.type);
            roleRepository.save(userRole);
        }

        User adminUser = userRepository.findByUsername(adminUserName);
        if(adminUser == null){
            adminUser = new User();
            adminUser.setUsername(adminUserName);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setEnabled(true);
            adminUser.setRoles(Arrays.asList(adminRole, userRole));
            userRepository.save(adminUser);
        }
//        long roleCOunt = roleRepository.count();

    }
}
