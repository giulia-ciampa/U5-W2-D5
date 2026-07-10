package giuliaciampa.U5_W2_D5.exceptions;


import giuliaciampa.U5_W2_D5.DTO.ErrorsDTO;
import giuliaciampa.U5_W2_D5.DTO.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException e) {
        return new ErrorsDTO(e.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationException(ValidationException e) {
        return new ErrorsWithListDTO(e.getMessage(), LocalDateTime.now(), e.getErrorsList());
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleFileException(FileException e) {
        return new ErrorsDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO hanldeNotFoundException(NotFoundException e) {
        return new ErrorsDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleInvalidFormat(HttpMessageNotReadableException e) {
        String messaggio = "Formato dati non valido.";

        if (e.getMessage().contains("LocalDate")) {
            messaggio = "Data non valida! Usa il formato yyyy-MM-dd.";
        } else if (e.getMessage().contains("StatoViaggio")) {
            messaggio = "Stato non valido! I valori ammessi sono: IN_PROGRAMMA o COMPLETATO (in maiuscolo).";
        }

        return new ErrorsDTO(messaggio, LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericException(Exception e) {
        return new ErrorsDTO("al momento il server non risponde", LocalDateTime.now());
    }


}
