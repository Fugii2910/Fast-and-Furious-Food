package br.fugii.eti.Fast_and_Furious_Food.api.exception;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiException {
    
     @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException ex){
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(Map.of(
                        "erro", ex.getReason()
                ));
    }
    
}
