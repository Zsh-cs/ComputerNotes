package com.sky.interceptor;

import com.sky.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器抽象父类（模板方法设计模式）
 */
@Slf4j
public abstract class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    protected JwtProperties jwtProperties;

    //Caution: 注意令牌过期问题！
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            // 当前拦截到的不是动态方法，直接放行
            return true;
        }

        // 1.从请求头中获取令牌
        String token = getAndTokenFromHeader(request);

        // 2.校验令牌
        try {
            log.info("jwt校验: {}", token);
            checkToken(token);
            // 3.通过，放行
            return true;
        } catch (Exception e){
            // 4.不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }

    // 钩子方法，由子类重写
    protected abstract String getAndTokenFromHeader(HttpServletRequest request);
    protected abstract void checkToken(String token);
}
