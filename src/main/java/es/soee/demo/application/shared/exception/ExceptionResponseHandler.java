package es.soee.demo.application.shared.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gloria R. Leyva Jerez
 * Create responses to handle exceptions thrown in the system
 */
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    private static final int ERROR_CODE_AlreadyExistsException = 1;
    private static final int ERROR_CODE_ResourceNotFoundException = 2;
    private static final int ERROR_CODE_ConstraintViolationException_MODEL = 3;
    private static final int ERROR_CODE_SignatureException = 4;
    private static final int ERROR_CODE_MalformedJwtException = 5;
    private static final int ERROR_CODE_ExpiredJwtException = 6;
    private static final int ERROR_CODE_UnsupportedJwtException = 7;
    private static final int ERROR_CODE_AuthenticationException = 8;

    private final ErrorContentHandler contentHandler;

    /**
     * Create response to handle an exception thrown by already exist name in database.
     * *
     *
     * @param e AlreadyExistsException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(AlreadyExistsException.class)
    @ApiResponse(responseCode = "409", description = Constants.alreadyExist_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.alreadyExist_resp_name, summary = "409 from the service directly",
                            value = Constants.alreadyExist_resp_example)}))
    public ResponseEntity<ErrorInfo> alreadyExist(HttpServletRequest request, AlreadyExistsException e) {
        String errorMessage = contentHandler.alreadyExistAlertMessage(e.getResourceName(), e.getFieldName(), String.valueOf(e.getFieldValue()));

        HttpStatus status = HttpStatus.CONFLICT;
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_AlreadyExistsException,
                "ERROR_CODE_AlreadyExistsException");

        return new ResponseEntity<>(errorInfo, contentHandler.alreadyExistAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by resource not found
     *
     * @param e ResourceNotFoundException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ApiResponse(responseCode = "404", description = Constants.notFound_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.notFound_resp_name, summary = "404 from the service directly",
                            value = Constants.notFound_resp_example)}))
    public ResponseEntity<ErrorInfo> notFound(HttpServletRequest request, ResourceNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorMessage = contentHandler.notFoundAlertMessage(e.getResourceName(), e.getFieldName(), String.valueOf(e.getFieldValue()));
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ResourceNotFoundException,
                "ResourceNotFoundException");

        return new ResponseEntity<>(errorInfo, contentHandler.notFoundAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by constraint violation in validation DTO process
     *
     * @param e ConstraintViolationException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ApiResponse(responseCode = "422", description = Constants.validationViolation_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.validationViolation_resp_name, summary = "422 from the service directly",
                            value = Constants.validationViolation_example)}))
    public ResponseEntity<ErrorInfo> methodValidationViolationConstraintHandler(HttpServletRequest request, MethodArgumentNotValidException e) {

        // get spring errors
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // convert errors to standard string
        StringBuilder errorMessage = new StringBuilder();

        AtomicInteger s = new AtomicInteger(fieldErrors.size());
        fieldErrors.forEach(f -> {
            errorMessage.append(f.getDefaultMessage());
            s.getAndDecrement();
            if (s.getPlain() != 0) {
                errorMessage.append(", ");
            }
        });

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String errorResponse = contentHandler.validationInModelAlertMessage(errorMessage.toString());
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorResponse,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ConstraintViolationException_MODEL,
                "ConstraintViolationException-javax-validation");

        return new ResponseEntity<>(errorInfo, contentHandler.validationInModelAlert(errorResponse), status);
    }

    /**
     * Create response to handle an exception thrown by constraint violation in validation DTO process
     *
     * @param e ConstraintViolationException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ApiResponse(responseCode = "422", description = Constants.validationViolation_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.validationViolation_resp_name, summary = "422 from the service directly",
                            value = Constants.validationViolation_example)}))
    public ResponseEntity<ErrorInfo> methodValidationViolationConstraintHandler(HttpServletRequest request, ConstraintViolationException e) {

        // get spring errors
        Set<ConstraintViolation<?>> result = e.getConstraintViolations();

        // convert errors to standard string
        StringBuilder error = new StringBuilder();

        AtomicInteger s = new AtomicInteger(result.size());
        result.forEach(f -> {
            error.append(f.getMessage());
            s.getAndDecrement();
            if (s.getPlain() != 0) {
                error.append(", ");
            }
        });

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String errorMessage = contentHandler.validationInModelAlertMessage(error.toString());
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ConstraintViolationException_MODEL,
                "ConstraintViolationException-javax-validation");

        return new ResponseEntity<>(errorInfo, contentHandler.validationInModelAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by SignatureException because token is invalid
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorInfo> invalidTokenBySignature(HttpServletRequest request, SignatureException e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage = e.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_SignatureException,
                "SignatureException");

        return new ResponseEntity<>(errorInfo, contentHandler.invalidToken(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by MalformedJwtException because token is invalid
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorInfo> invalidTokenByMalformed(HttpServletRequest request, MalformedJwtException e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage = e.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_MalformedJwtException,
                "MalformedJwtException");

        return new ResponseEntity<>(errorInfo, contentHandler.invalidToken(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by MalformedJwtException because token is invalid
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorInfo> invalidTokenByExpired(HttpServletRequest request, ExpiredJwtException e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage = e.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ExpiredJwtException,
                "ExpiredJwtException");

        return new ResponseEntity<>(errorInfo, contentHandler.invalidToken(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by MalformedJwtException because token is invalid
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorInfo> invalidTokenByExpired(HttpServletRequest request, UnsupportedJwtException e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage = e.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_UnsupportedJwtException,
                "UnsupportedJwtException");

        return new ResponseEntity<>(errorInfo, contentHandler.invalidToken(errorMessage), status);
    }
}

