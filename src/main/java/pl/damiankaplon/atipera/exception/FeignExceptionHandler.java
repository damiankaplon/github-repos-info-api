package pl.damiankaplon.atipera.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class FeignExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {FeignException.class})
    public ResponseEntity<ErrorResponse> handle(FeignException exception) {
        return ResponseEntity.status(exception.status())
                .body(
                        new ErrorResponse(exception.status(), exception.getMessage())
                );
    }


}
