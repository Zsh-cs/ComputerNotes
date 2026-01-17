package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户端jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor extends JwtTokenInterceptor{

    // 从请求头中获取用户令牌
    @Override
    protected String getAndTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(jwtProperties.getUserTokenName());
    }

    // 校验用户令牌，并将userId存入ThreadLocal中
    @Override
    protected void checkToken(String token) {
        Claims claims = JwtUtil.parseJwt(jwtProperties.getUserSecretKey(), token);
        Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
        log.info("id={}的微信用户登入\n", userId);
        // BaseContext内部使用了ThreadLocal技术，它是线程隔离的
        // 不会与BaseContext.setCurrentId(empId);发生冲突
        BaseContext.setCurrentId(userId);
    }
}
