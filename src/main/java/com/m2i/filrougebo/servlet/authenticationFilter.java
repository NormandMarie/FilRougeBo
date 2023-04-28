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
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebFilter(urlPatterns = "/secured/*")
public class authenticationFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        boolean isAuthenticated = false;
        HttpSession session = request.getSession();
        if (session != null) {
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            isAuthenticated = Authentication.authenticate(username, password);
        }

        // Vérification des privilèges de l'utilisateur
        if (isAuthenticated) {
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            if (Authentication.authenticate(username, password)) {

                chain.doFilter(request, response); // Accès autorisé pour les Admins
            }
            else {
                response.sendRedirect(request.getContextPath() + "/WEB-INF/login.jsp"); // Redirection vers la page de connexion pour les utilisateurs non authentifiés
            }
        }
    }
}
