package com.primebank.blog.exception.unauthorized;

import com.primebank.blog.util.response.ResponseModel;
import com.primebank.blog.util.response.model.Response;
import com.primebank.blog.util.response.model.ResponseError;
import com.primebank.blog.util.response.transformer.ResponseAssembler;
import com.primebank.blog.util.response.transformer.ResponseErrorAssembler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UnauthExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthExceptionHandler.class);
    private final ResponseModel responseModel;

    public UnauthExceptionHandler(@Qualifier("responseModel") ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public void handleAuthenticationException(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        LOGGER.error(e.getMessage(), e);
        ResponseError responseError = ResponseErrorAssembler.toResponseError(e.getMessage(), HttpStatus.UNAUTHORIZED);
        Response<ResponseError> errorResponse = ResponseAssembler.toResponse(HttpStatus.UNAUTHORIZED, responseError);
        responseModel.writeResponse(response, errorResponse);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        LOGGER.error(e.getMessage(), e);
        ResponseError responseError = ResponseErrorAssembler.toResponseError("You aren't permitted to access this resource", HttpStatus.FORBIDDEN);
        Response<ResponseError> errorResponse = ResponseAssembler.toResponse(HttpStatus.FORBIDDEN, responseError);
        responseModel.writeResponse(response, errorResponse);
    }

}
