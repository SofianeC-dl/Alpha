package com.alphabackend.exception;

import com.alphabackend.model.ErrorResponse;
import com.alphabackend.model.enum_model.ErrorHttpEnum;
import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Voir EXCEPTION_DATA("{0}|{1}|{2}"); pour :
     *  String message = messageSplit[0];
     * <p>
     *  Récupération de toutes les exceptions non personnalisé
     * @param ex exception attrapé
     * @param request requête qui a crée l'exception
     * @return Objet d'erreur
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        String stackTrace = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        String[] messageSplit = ex.getMessage().split("\\|");
        String message = messageSplit[0];
        ErrorResponse error = Utils.createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                message,
                request.getDescription(false),
                stackTrace,
                ErrorHttpEnum.INTERNAL_ERROR.getException().toLowerCase());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Voir EXCEPTION_DATA("{0}|{1}|{2}"); pour :
     *  String message = messageSplit[0];
     *  String labelObject = messageSplit[1];
     *  String typeRequest = messageSplit[2];
     * <p>
     * Récupération du ResourceNotFoundException
     * @param ex exception attrapé
     * @param request requête qui a crée l'exception
     * @return Objet d'erreur
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        String stackTrace = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        logger.error("Resource not found", ex);

        String[] messageSplit = ex.getMessage().split("\\|");
        String message = messageSplit[0];
        String labelObject = messageSplit[1];
        String typeRequest = messageSplit[2];

        ErrorResponse error = Utils.createErrorResponse(
                HttpStatus.NOT_FOUND,
                message,
                request.getDescription(false),
                stackTrace,
                labelObject,
                ErrorHttpEnum.RESOURCE_NOT_FOUND.getException().toLowerCase(),
                typeRequest.toLowerCase());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Voir EXCEPTION_DATA("{0}|{1}|{2}"); pour :
     *  String message = messageSplit[0];
     *  String labelObject = messageSplit[1];
     *  String typeRequest = messageSplit[2];
     * <p>
     * Récupération du BadCredentialsException
     * @param ex exception attrapé
     * @param request requête qui a crée l'exception
     * @return Objet d'erreur
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(BadCredentialsException ex, WebRequest request){
        String stackTrace = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        logger.error(ErrorTextEnum.BAD_CREDENTIALS.getText(), ex);

        String[] messageSplit = ex.getMessage().split("\\|");
        String message = messageSplit[0];
        String labelObject = messageSplit[1];
        String typeRequest = messageSplit[2];

        ErrorResponse error = Utils.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                message,
                request.getDescription(false),
                stackTrace,
                labelObject,
                ErrorHttpEnum.BAD_CREDENTIALS.getException().toLowerCase(),
                typeRequest.toLowerCase());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private static @NotNull String getBody(WebRequest request) throws UnsupportedEncodingException{
        String requestBody = "";
        if (request instanceof ServletWebRequest servletwebrequest) {
            HttpServletRequest servletRequest = servletwebrequest.getRequest();
            if (servletRequest instanceof ContentCachingRequestWrapper cachingRequest) {
                byte[] buf = cachingRequest.getContentAsByteArray();
                if (buf.length > 0) {
                    requestBody = new String(buf, cachingRequest.getCharacterEncoding());
                }
            }
        }
        return requestBody;
    }
}
