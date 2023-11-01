package ru.mixaron.secuirty.controllers.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mixaron.secuirty.dto.PersonDTO;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    // вме что в controllers/auth переделать как обычно
    private final AuthenticationService service;

    // в этих контроллерах есть РеспонсЕнтити и РеквестБоди со своими методами
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid PersonDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getField());
        }
        service.register(request);
        return ResponseEntity.status(HttpStatus.OK).body("Ok"); // ResponseEntity возвращает http ответ json
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody @Valid PersonDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthenticationResponse.builder().build());
        }
        return ResponseEntity.ok(service.auth(request));
    }
}
