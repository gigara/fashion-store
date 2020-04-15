package com.giga.FashionStore.controller;

import com.giga.FashionStore.model.Role;
import com.giga.FashionStore.model.Roles;
import com.giga.FashionStore.model.StoreManager;
import com.giga.FashionStore.model.User;
import com.giga.FashionStore.repository.RoleRepository;
import com.giga.FashionStore.repository.UserRepository;
import com.giga.FashionStore.request.SignUpRequest;
import com.giga.FashionStore.response.MessageResponse;
import com.giga.FashionStore.service.SequenceGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * route controller for admin's task requests.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SequenceGenerateService sequenceGenerateService;
    @Autowired
    PasswordEncoder encoder;

    // add store manager
    @PostMapping("/createmanager")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> creatManager(@Valid @RequestBody SignUpRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        StoreManager user = new StoreManager(sequenceGenerateService.generateSequence(User.SEQUENCE_NAME),
                request.getFirstName(), request.getLastName(),
                request.getEmail(),
                request.getEmail(),
                encoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(Roles.ROLE_STORE_MANAGER)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        roles.add(userRole);

        // add roles to the created user
        user.setRoles(roles);
        // save the user into the db
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
