package ru.mixaron.secuirty.util.errorAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;


// лучше было сделать в контроллерах  обработку некоторых ошибок
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> UnknownException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("Неизвестная ошибка", new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> emailNotUnique(DataIntegrityViolationException ex) {
        String errorMessage ;
        if (ex.getMessage().contains("shop_category_category_name_key")) {
            errorMessage = "Такая категория уже существует";
        } else if (ex.getMessage().contains("shop_product_pkey")) {
            errorMessage = "Такой товар уже существует!";
        } else{
            errorMessage = "Такой email уже существует!";
        }
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundEmailException.class)
    public ResponseEntity<ErrorResponse> emailNotFound(NotFoundEmailException e) {
        ErrorResponse errorResponse = new ErrorResponse("Такого пользователя не существует", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundProductException.class)
    public ResponseEntity<ErrorResponse> productNotFound(NotFoundProductException e) {
        ErrorResponse errorResponse = new ErrorResponse("Такого продукта не существует", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreateCategoryException.class)
    public ResponseEntity<ErrorResponse> categoryCreateException(CreateCategoryException e) {
        ErrorResponse errorResponse = new ErrorResponse("Такого продукта не существует", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundOrderById.class)
    public ResponseEntity<ErrorResponse> findByIdException(NotFoundOrderById e) {
        ErrorResponse errorResponse = new ErrorResponse("Такого заказа не существует", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> BadCredentials(BadCredentialsException e) {
        ErrorResponse errorResponse = new ErrorResponse("Неправильные данные для входа", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(UnkownPassword.class)
    public ResponseEntity<ErrorResponse> UnkownPassword(UnkownPassword e) {
        ErrorResponse errorResponse = new ErrorResponse("Password problem", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // не работает
//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ErrorResponse> jwtExpired(ExpiredJwtException e) {
//        ErrorResponse errorResponse = new ErrorResponse("JWT Токен проссрочен", new Date());
//        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//    }
}
