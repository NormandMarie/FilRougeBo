package com.m2i.filrougebo.servlet.Log;

import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.service.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
 AuthenticationService authentication = new AuthenticationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin user = authentication.authenticatewithSuper(username, password);
        if (user != null) {
            // Si les informations de connexion sont correctes, on stocke les données de l'utilisateur dans la session et on redirige vers la page d'accueil
            HttpSession session = req.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("isSuperAdmin", user.getIsSuperAdmin());
            resp.sendRedirect(req.getContextPath());
        } else {
            // Si les informations de connexion sont incorrectes, on renvoie l'utilisateur vers la page de connexion avec un message d'erreur
            req.setAttribute("isError", true);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
