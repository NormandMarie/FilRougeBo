package com.m2i.filrougebo.servlet.admin;

import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = DeleteAdminServlet.URL)
public class DeleteAdminServlet extends HttpServlet {

    public static final String URL = "/SuperAdmin/delete-admin";
    private static final String JSP = "/WEB-INF/error.jsp";
    private AdminService adminService = new AdminService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        //  récupérer l'admin à supprimer
        Admin adminToDelete = adminService.findById(id);

        // vérification si l'admin existe
        if(adminToDelete != null) {
            // méthode pour supprimer l'admin
            adminService.delete(adminToDelete);

            // redirection
            response.sendRedirect(ListAdminServlet.URL);
        } else {
            // si l'admin n'existe pas, on affiche un message d'erreur
            request.setAttribute("errorMessage", "Admin not found");
            request.getRequestDispatcher(JSP).forward(request, response);
        }
    }

}
