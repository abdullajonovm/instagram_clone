package com.example.instagram0404.controller;

import com.example.instagram0404.playload.LoginDTO;
import com.example.instagram0404.security.JwtProvider;
import com.example.instagram0404.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    final JwtProvider jwtProvider;
    final AuthService authService;
;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginDTO) {
        //login qiladi Tizimda bor bo'lsa token generate qilishimz kerak

        UserDetails userDetails = authService.loadUserByUsername(loginDTO.getName());
        String token = jwtProvider.generateToken(loginDTO.getName());


//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("Salom do'st");
//        message.setSubject("Title bu akasi!");
//        message.setSentDate(new Date());
//        message.setTo("jafarbek1997@gmail.com");
//        message.setFrom("pdp.uz@gmail.com");
//
//        JavaMailSender mailSender = emailConfig.send();
//        mailSender.send(message);

        return ResponseEntity.ok().body(token);
    }





    //400 xatolik bo'lganda  aynan shu(MethodArgumentNotValidException) toifali xatolikni ushlaydi
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
