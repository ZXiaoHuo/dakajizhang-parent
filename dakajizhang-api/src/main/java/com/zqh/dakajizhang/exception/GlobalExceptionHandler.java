package com.zqh.dakajizhang.exception;

import com.alibaba.nacos.api.common.ResponseCode;
import com.netflix.client.ClientException;
import com.zqh.dakajizhang.dto.RestResult;
import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @author zhangqh
 * @date 2021/9/1 0001 10:28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    public RestResult globalException(GlobalException e) {
        return RestResult.error(e.getMessage());
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public RestResult expiredJwtException(ExpiredJwtException e) {
        e.printStackTrace();
        return RestResult.error(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED),"登录超时");
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public RestResult runTimeException(HttpServletRequest req, AuthenticationException e) {
        e.printStackTrace();
        return RestResult.error(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED),e.getMessage());
    }

    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public RestResult internalAuthenticationServiceException(HttpServletRequest req, AuthenticationException e) {
        return RestResult.error(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED),"登录超时");
    }

    @ExceptionHandler(value = FeignException.class)
    public RestResult feignException(FeignException e) {
        return RestResult.error(String.valueOf(e.status()),e.getMessage());
    }

    @ExceptionHandler(value = ClientException.class)
    public RestResult clientException(ClientException e) {
        return RestResult.error(String.valueOf(e.getErrorCode()),e.getMessage());
    }

    @ExceptionHandler(value = SocketTimeoutException.class)
    public RestResult socketTimeoutException( SocketTimeoutException e) {
        return RestResult.error(RestResult.SERVER_TIMEOUT,e.getMessage());
    }

    @ExceptionHandler(value = SocketException.class)
    public RestResult socketException(SocketException e) {
        return RestResult.error(e.getMessage());
    }

}
