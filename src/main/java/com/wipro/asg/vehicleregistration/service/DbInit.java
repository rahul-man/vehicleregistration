package com.wipro.asg.vehicleregistration.service;

import com.wipro.asg.vehicleregistration.model.User;
import com.wipro.asg.vehicleregistration.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.wipro.asg.vehicleregistration.model.ApplicationUserRole.ADMIN;
import static com.wipro.asg.vehicleregistration.model.ApplicationUserRole.USER;

@Service
public class DbInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        User admin = new User(
                "admin123",
                passwordEncoder.encode("admin123"),
                ADMIN, ADMIN.getAuthorities());

        User customer = new User(
                "customer",
                passwordEncoder.encode("customer123"),
                USER, USER.getAuthorities());

        List<User> users = Arrays.asList(admin, customer);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
