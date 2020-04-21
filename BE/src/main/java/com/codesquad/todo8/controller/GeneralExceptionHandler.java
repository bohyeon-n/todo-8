package com.codesquad.todo8.controller;

import com.codesquad.todo8.error.CardNotFoundException;
import com.codesquad.todo8.error.UnauthorizedException;
import com.codesquad.todo8.error.UserNotFoundException;
import com.codesquad.todo8.model.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

  private ResponseEntity<ApiResult> newResponse(Exception exception, HttpStatus status) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("ContentType", "application/json");
    return new ResponseEntity<>(ApiResult.ERROR(exception, status), httpHeaders, status);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> userNotFoundHandler(UserNotFoundException e) {
    logger.debug("UserNotFound, {}", e.getMessage());
    return newResponse(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CardNotFoundException.class)
  public ResponseEntity<?> cardNotFoundHandler(CardNotFoundException e) {
    logger.debug("CardNotFound, {}", e.getMessage());
    return newResponse(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<?> unauthorizedHandler(UnauthorizedException e) {
    logger.debug("Unauthorized, {}", e.getMessage());
    return newResponse(e, HttpStatus.UNAUTHORIZED);
  }


}
