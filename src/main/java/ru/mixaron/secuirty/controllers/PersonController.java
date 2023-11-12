package ru.mixaron.secuirty.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.secuirty.Service.PersonService;
import ru.mixaron.secuirty.dto.ChangePasswordDTO;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.repositories.PersonRepo;

@RestController
@AllArgsConstructor
@RequestMapping("/api/changePerson")
public class PersonController {

    private final PersonService personService;
    @PutMapping("/password")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody ChangePasswordDTO passwordDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
            personService.changePassword(passwordDTO.getOldPassword(), passwordDTO.getNewPassword(), mail);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/email")
    public ResponseEntity<HttpStatus> changeEmail(@RequestBody ChangePasswordDTO passwordDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (!personService.changeEmail(email, passwordDTO.getEmail())) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
