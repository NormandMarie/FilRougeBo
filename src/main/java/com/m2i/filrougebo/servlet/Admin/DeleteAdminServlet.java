package com.m2i.filrougebo.servlet.Admin;

import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/SuperAdmin/delete-admin")
public class DeleteAdminServlet extends HttpServlet {
    AdminService adminService = new AdminService();protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        //  récupérer l'admin à supprimer
        Admin adminToDelete = adminService.findById(id);

        // vérification si l'admin existe
        if(adminToDelete != null) {
            // méthode pour supprimer l'admin
            adminService.delete(adminToDelete);

            // redirection
            response.sendRedirect(request.getContextPath() + "/SuperAdmin/ListAdmin");
        } else {
            // si l'admin n'existe pas, on affiche un message d'erreur
            request.setAttribute("errorMessage", "Admin not found");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

}
