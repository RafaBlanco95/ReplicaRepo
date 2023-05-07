package com.salesianas.dam.replica.controller.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.salesianas.dam.replica.controller.AuthControllerRest;
import com.salesianas.dam.replica.dto.ERole;
import com.salesianas.dam.replica.payload.request.LoginRequest;
import com.salesianas.dam.replica.payload.request.SignupRequest;
import com.salesianas.dam.replica.payload.response.JwtResponse;
import com.salesianas.dam.replica.payload.response.MessageResponse;
import com.salesianas.dam.replica.persistence.entity.RoleEntity;
import com.salesianas.dam.replica.persistence.entity.UserEntity;
import com.salesianas.dam.replica.persistence.repository.RoleRepository;
import com.salesianas.dam.replica.persistence.repository.UserRepository;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import com.salesianas.dam.replica.utils.security.jwt.JwtUtils;
import com.salesianas.dam.replica.utils.security.services.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description ="Authentication rest")
public class AuthControllerRestImpl {
    public class AuthControllerImpl implements AuthControllerRest {
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        UserRepository userRepository;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        PasswordEncoder encoder;
        @Autowired
        JwtUtils jwtUtils;

        @Override
        @PostMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_SIGNIN)
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(
                    new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
        }

        @Override
        @PostMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_SIGNUP)
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse(RestConstantsUtils.RESOURCE_DUPLICATE_USER_ERROR));
            }
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse(RestConstantsUtils.RESOURCE_DUPLICATE_EMAIL_ERROR));
            }
// Create new user's account
            UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));
            Set<String> strRoles = signUpRequest.getRole();
            Set<RoleEntity> roles = new HashSet<>();
            if (strRoles == null) {
                RoleEntity userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                        .orElseThrow(() -> new RuntimeException(RestConstantsUtils.RESOURCE_ROLE_NOT_FOUND_ERROR));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException(RestConstantsUtils.RESOURCE_ROLE_NOT_FOUND_ERROR));
                            roles.add(adminRole);
                            break;
                        case "employee":
                            RoleEntity employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                    .orElseThrow(() -> new RuntimeException(RestConstantsUtils.RESOURCE_ROLE_NOT_FOUND_ERROR));
                            roles.add(employeeRole);
                            break;
                        case "teacher":
                            RoleEntity teacherRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                                    .orElseThrow(() -> new RuntimeException(RestConstantsUtils.RESOURCE_ROLE_NOT_FOUND_ERROR));
                            roles.add(teacherRole);
                            break;
                        default:
                            RoleEntity studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                                    .orElseThrow(() -> new RuntimeException(RestConstantsUtils.RESOURCE_ROLE_NOT_FOUND_ERROR));
                            roles.add(studentRole);
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse(RestConstantsUtils.USER_REGISTER_SUCCESS));
        }
    }
}
