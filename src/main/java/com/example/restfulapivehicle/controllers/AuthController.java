package com.example.restfulapivehicle.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulapivehicle.models.EnumRole;
import com.example.restfulapivehicle.models.Role;
import com.example.restfulapivehicle.models.User;
import com.example.restfulapivehicle.repositories.RoleRepository;
import com.example.restfulapivehicle.repositories.UserRepository;
import com.example.restfulapivehicle.responses.MessageResponse;
import com.example.restfulapivehicle.responses.UserInfoResponse;
import com.example.restfulapivehicle.security.jwt.JwtUtils;
import com.example.restfulapivehicle.security.services.UserDetailsImpl;
import com.example.restfulapivehicle.validations.LoginValidation;
import com.example.restfulapivehicle.validations.SignupValidation;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
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

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> fetchAll(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<User> prices = new ArrayList<>();
            PageRequest paging = PageRequest.of(page - 1, 5);
            Page<User> pageResult = userRepository.findAll(paging);
            prices = pageResult.getContent();
            data.put("status_code", 200);
            data.put("data", prices);
            data.put("total", pageResult.getTotalElements());
            data.put("limit", pageResult.getSize());
            data.put("skip", pageResult.getPageable().getOffset());
            return new ResponseEntity<Object>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("status_code", 500);
            data.put("message", e.getMessage());
            return new ResponseEntity<Object>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> update(@Valid @RequestBody SignupValidation validation, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            User entity = userRepository.findById(validation.getId()).get();
            Set<Role> roles = new HashSet<>();
            Set<String> strRoles = validation.getRole();
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
            entity.setId(validation.getId());
            entity.setPassword(encoder.encode(validation.getPassword()));
            entity.setEmail(validation.getEmail());
            entity.setRoles(roles);
            entity.setUpdated_at(LocalDateTime.now());
            try {
                userRepository.save(entity);
                response.put("status_code", 200);
                response.put("message", "data updated");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                response.put("status_code", 500);
                response.put("message", e.getMessage());
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
                response.put("status_code", 500);
                response.put("message", e.getMessage());
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NoSuchElementException e) {
            response.put("status_code", 404);
            response.put("message", "data not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginValidation loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupValidation signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> show(@PathVariable int id) {
        Map<String, Object> data = new HashMap<>();
        try {
            User entity = userRepository.findById(id).get();
            data.put("status_code", 200);
            data.put("data", entity);
            return new ResponseEntity<Object>(data, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            data.put("status_code", 404);
            data.put("message", "data not found");
            return new ResponseEntity<Object>(data, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            data.put("status_code", 500);
            data.put("message", e.getMessage());
            return new ResponseEntity<Object>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            User entity = userRepository.findById(id).get();
            userRepository.delete(entity);
            response.put("status_code", 200);
            response.put("message", "data deleted");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {

            response.put("status_code", 404);
            response.put("message", "data not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
