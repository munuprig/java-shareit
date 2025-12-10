package ru.practicum.shareit.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, ItemIsNotAvailableException.class,
            WrongDatesException.class, BookingCanBeApprovedOnlyByOwnerException.class,
            UnsupportedStatusException.class, NotBookerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validateException(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Ошибка валидации",e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> validateException(final ConstraintViolationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class, NotAvailableToBookOwnItemsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse entityNotFoundException(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Класс не найден",e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse illegalVewAndUpdateException(final IllegalVewAndUpdateException e) {
        log.error(e.getMessage());
        return new ErrorResponse(
                "Подтвердить бронирование может только собственник вещи",
                e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse userNotFoundException(final UserNotFoundException e) {
        log.error(e.getMessage());
        return new ErrorResponse(
                "Пользователь не найден",
                e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse userNotUniqueEmailException(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        return new ErrorResponse(
                "Email %s уже используется.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable e) {
        log.error(e.getMessage());
        return new ErrorResponse(
                "Произошла непредвиденная ошибка.",
                e.getMessage()
        );
    }
}
