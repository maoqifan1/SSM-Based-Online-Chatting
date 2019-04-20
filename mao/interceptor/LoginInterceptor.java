package com.mao.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 登陆拦截器
 * 用于防止用户在未登陆的情况下访问需要登陆才能访问的页面
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取session
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj != null) {
            return true;
        }

        // 获取请求的url
        String url = request.getRequestURI();
        // 登陆或者访问login.jsp不拦截,注册不拦截
        if ((url.contains("/loginInput") || url.contains("/login") || url.contains("/regInput") || url.contains("/register"))) {
            return true;
        }


        // 没有登陆且访问除登陆界面以外的界面时，对请求进行转发
        request.getRequestDispatcher("/user/loginInput").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
