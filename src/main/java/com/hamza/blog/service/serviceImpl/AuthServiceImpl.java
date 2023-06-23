package com.hamza.blog.service.serviceImpl;

import com.hamza.blog.entities.Role;
import com.hamza.blog.entities.User;
import com.hamza.blog.exceptions.BlogApiException;
import com.hamza.blog.payload.LoginDto;
import com.hamza.blog.payload.RegisterDto;
import com.hamza.blog.repositories.RoleRepository;
import com.hamza.blog.repositories.UserRepository;
import com.hamza.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder

                           ) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
     Authentication authentication =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User login successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {

        // Add check for username if already exist
        if(userRepository.existsByUsername(registerDto.getUsername())) {
          throw new BlogApiException(HttpStatus.BAD_REQUEST,"Username already exist");
        }

        // Add check for email if already exist
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"email already exist");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles= new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User registred successfully";
    }
}
