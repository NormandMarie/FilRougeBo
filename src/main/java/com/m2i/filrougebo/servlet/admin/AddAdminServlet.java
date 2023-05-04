package com.m2i.filrougebo.servlet.admin;

import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = AddAdminServlet.URL)
public class AddAdminServlet extends HttpServlet {

    public static final String URL = "/SuperAdmin/add-admin";
    private static final String JSP = "/WEB-INF/admin/AddAdmin.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean superAdmin  = Boolean.parseBoolean(req.getParameter("superAdmin"));

        Admin newAdmin = new Admin(username, superAdmin, password);

        AdminService adminService = new AdminService();
        adminService.create(newAdmin);

        resp.sendRedirect(ListAdminServlet.URL);
    }
}

