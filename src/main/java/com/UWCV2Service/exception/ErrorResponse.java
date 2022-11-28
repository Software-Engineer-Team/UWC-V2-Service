package com.UWCV2Service.exception;

import lombok.RequiredArgsConstructor;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ErrorResponse
 */
@RestControllerAdvice
@Component
@RequiredArgsConstructor
public class ErrorResponse {
  @ExceptionHandler({UsernameNotFoundException.class, Exception.class})
  private ResponseEntity<?> handle(Exception ex) throws JSONException {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("error", ex.getMessage());
    return new ResponseEntity<Object>(jsonObject.toString(),
                                      HttpStatus.NOT_FOUND);
  }
}
