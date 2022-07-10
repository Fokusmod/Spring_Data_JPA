package ru.geekbrains.market.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.dto.RegistrationRequest;
import ru.geekbrains.market.exception.MarketError;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repository.UserRepository;
import ru.geekbrains.market.service.RegistrationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RegistrationController {

    private final UserRepository userRepository;

    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<?> tryToRegistration(@RequestBody RegistrationRequest registrationRequest) {
        boolean isEmpty = userRepository.findByUsername(registrationRequest.getUsername()).isEmpty();
        if (isEmpty) {
            registrationService.save(registrationRequest);
            return new ResponseEntity<>(new MarketError("Registration successful"), HttpStatus.CREATED);
        }
            return new ResponseEntity<>(new MarketError("This user " + registrationRequest.getUsername() + " already exist"), HttpStatus.BAD_REQUEST);
    }
}
