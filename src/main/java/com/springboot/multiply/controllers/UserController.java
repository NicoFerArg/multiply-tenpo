package com.springboot.multiply.controllers;

import com.springboot.multiply.models.Historial;
import com.springboot.multiply.models.LoginRequest;
import com.springboot.multiply.models.LoginResponse;
import com.springboot.multiply.models.User;
import com.springboot.multiply.security.JwtTokenUtil;
import com.springboot.multiply.services.HistorialService;
import com.springboot.multiply.services.UserDetailsImpl;
import com.springboot.multiply.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private HistorialService historialService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsImpl userDetailsService;

    UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, HistorialService historialService){

        this.userService = userService;
        this.historialService = historialService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@Valid @RequestBody User user) {
        Historial historial = new Historial();
        historial.setEndpoint("/users/signup");
        historial.setUsuario(user.getUsername());
        historial.setDateStart(Calendar.getInstance().getTime());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        historialService.addHistorial(historial);
        return "User added. The new user is "+user.getUsername();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest authenticationRequest) throws Exception {

        Historial historial = new Historial();
        historial.setEndpoint("/users/login");
        historial.setUsuario(authenticationRequest.getUsername());
        historial.setDateStart(Calendar.getInstance().getTime());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        historialService.addHistorial(historial);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
