package com.m2i.filrougebo.servlet.Filter;

import com.m2i.filrougebo.service.AuthenticationService;
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
    AuthenticationService authenticationService = new AuthenticationService();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String requestUri = request.getRequestURI().substring(request.getContextPath().length());


        boolean isSuperAdmin = session.getAttribute("isSuperAdmin") != null && (boolean) session.getAttribute("isSuperAdmin");
        System.out.println(session.getAttribute("isSuperAdmin"));
        if (isSuperAdmin) {
            // L'utilisateur est un super-admin
            chain.doFilter(req, resp);
        } else {
            // L'utilisateur n'est pas un super-admin
            response.sendRedirect(request.getContextPath() + "/error");
        }

    }
}
