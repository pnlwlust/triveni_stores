package com.proha.TriveniStores.service;

import com.proha.TriveniStores.domain.*;
import com.proha.TriveniStores.repository.RegistrationTokenRepository;
import com.proha.TriveniStores.repository.RoleRepository;
import com.proha.TriveniStores.repository.UserRepository;
import com.proha.TriveniStores.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ComponentScan({"com.proha.TriveniStores.config"})
public class UserManagementService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegistrationTokenRepository registrationTokenRepository;
    @Autowired
    private MailService mailService;

    public User findUserByUsername(String username){

        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByAuthority(Roles.USER.type);
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);

        final RegistrationToken registrationToken = new RegistrationToken(user);

        saveRegistrationToken(registrationToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            Collection<GrantedAuthority> roles =null;
            try{
                roles = getUserAuthority(user.getRoles());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
                    roles);
        } else {
            throw new UsernameNotFoundException("could not find the user '" + username + "'");
        }
    }

    private Collection<GrantedAuthority> getUserAuthority(Collection<Role> userRoles) {
        Collection<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getAuthority()));
        });
        return new ArrayList<>(roles);
    }

    public void saveRegistrationToken(RegistrationToken token) {
        registrationTokenRepository.save(token);
    }
    public void deleteConfirmationToken(Long id){

        registrationTokenRepository.deleteById(id);
    }

    public void confirmUser(RegistrationToken token) {

        final User user = token.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        deleteConfirmationToken(token.getId());
    }

    public void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
                        + token);

        mailService.sendEmail(mailMessage);
    }
}
