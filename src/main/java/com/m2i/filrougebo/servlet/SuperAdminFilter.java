package com.m2i.filrougebo.servlet;

import com.m2i.filrougebo.service.Authentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/SuperAdmin/*")
public class SuperAdminFilter extends HttpFilter {
    private static final List<String> ALLOWED_URLS = Arrays.asList( "/secured/logout");
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String requestUri = request.getRequestURI().substring(request.getContextPath().length());

        if (session.getAttribute("username") != null || ALLOWED_URLS.contains(requestUri)) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}