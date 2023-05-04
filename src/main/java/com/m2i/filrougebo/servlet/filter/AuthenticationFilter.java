package com.m2i.filrougebo.servlet.filter;

import com.m2i.filrougebo.servlet.log.LoginServlet;
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

@WebFilter(urlPatterns = AuthenticationFilter.URL)
public class AuthenticationFilter extends HttpFilter {

    public static final String URL = "/secured/*";
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();

        if (session.getAttribute("username") != null) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(LoginServlet.URL);
        }
  }
}
