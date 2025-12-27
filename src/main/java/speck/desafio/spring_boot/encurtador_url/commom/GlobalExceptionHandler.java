package speck.desafio.spring_boot.encurtador_url.commom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import speck.desafio.spring_boot.encurtador_url.exception.ExpiredUrlException;
import speck.desafio.spring_boot.encurtador_url.exception.UrlNotFoundException;

import java.net.URISyntaxException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredUrlException.class)
    public ResponseEntity<?> handleExpiredUrlException(ExpiredUrlException ex){
        ex.printStackTrace();
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<?> handleUrlNotFoundException(UrlNotFoundException ex){
        ex.printStackTrace();
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex){
        ex.printStackTrace();
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenerico(Exception ex){
        ex.printStackTrace();
        HashMap<String, String> response = new HashMap<>();
        response.put("message", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
