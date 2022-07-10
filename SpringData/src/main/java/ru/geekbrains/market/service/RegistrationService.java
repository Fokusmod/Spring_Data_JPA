package ru.geekbrains.market.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.dto.RegistrationRequest;
import ru.geekbrains.market.model.Privilege;
import ru.geekbrains.market.model.Role;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repository.RoleRepository;
import ru.geekbrains.market.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void save(RegistrationRequest registrationRequest) {
        System.out.println(registrationRequest);
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(()-> new RuntimeException("Role has not found"));
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }


}
