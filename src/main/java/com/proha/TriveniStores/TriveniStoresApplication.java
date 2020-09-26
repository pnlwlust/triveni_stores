package com.proha.TriveniStores;

import com.proha.TriveniStores.repository.RoleRepository;
import com.proha.TriveniStores.repository.UserRepository;
import com.proha.TriveniStores.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.proha.TriveniStores"})  // scan JPA entities
public class TriveniStoresApplication extends ServletInitializer{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Bean
	CommandLineRunner init() {

		return args -> {

			DataInit.initUsers(userRepository, roleRepository, userRoleRepository, passwordEncoder);
		};

	}

	public static void main(String[] args) {
		SpringApplication.run(TriveniStoresApplication.class, args);
	}
}
