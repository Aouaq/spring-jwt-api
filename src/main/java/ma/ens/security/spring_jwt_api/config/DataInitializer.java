package ma.ens.security.spring_jwt_api.config;

import ma.ens.security.spring_jwt_api.entities.Role;
import ma.ens.security.spring_jwt_api.entities.User;
import ma.ens.security.spring_jwt_api.repositories.RoleRepository;
import ma.ens.security.spring_jwt_api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // Create roles
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setActive(true);
            admin.setRoles(Collections.singletonList(adminRole));
            userRepository.save(admin);

            // Create regular user
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setActive(true);
            user.setRoles(Collections.singletonList(userRole));
            userRepository.save(user);

            System.out.println("Database initialized with test users!");
        };
    }
}