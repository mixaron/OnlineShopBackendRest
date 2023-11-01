package ru.mixaron.secuirty.controllers.auth;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mixaron.secuirty.repositories.PersonRepo;
import ru.mixaron.secuirty.config.JwtService;
import ru.mixaron.secuirty.dto.PersonDTO;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.util.Role;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundEmailException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    // тут происходит регистрация и авторизация по токену

    private final PersonRepo personRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    // по идее, это вообще можно не делать(кроме userRepo.save)
    public AuthenticationResponse register(PersonDTO request) {
        var user = Person.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        personRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token("Ok").build();
    }

    public AuthenticationResponse auth(PersonDTO request) {
//        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
//        } catch (AuthenticationException e) {
//            throw new ExpiredJwtException(null, null, "JWT expired");
//        }
        var user = personRepo.findByEmail(request.getEmail()).orElseThrow(NotFoundEmailException::new);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
