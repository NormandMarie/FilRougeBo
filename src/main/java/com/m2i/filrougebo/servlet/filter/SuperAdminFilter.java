package com.m2i.filrougebo.servlet.filter;

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

@WebFilter(urlPatterns = SuperAdminFilter.URL)
public class SuperAdminFilter extends HttpFilter {
    public static final String URL = "/SuperAdmin/*";
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
            // TODO: Marie Norman check here
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain");
            response.getWriter().write("401 Unauthorized");
        }

    }
}
