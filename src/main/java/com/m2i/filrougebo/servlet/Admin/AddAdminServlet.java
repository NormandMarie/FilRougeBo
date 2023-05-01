package com.m2i.filrougebo.servlet.Admin;

import com.m2i.filrougebo.Dto.AdminDto;
import com.m2i.filrougebo.dao.AdminDao;
import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/SuperAdmin/add-admin")
public class AddAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/AddAdmin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean superAdmin  = Boolean.parseBoolean(req.getParameter("superAdmin"));

        Admin newAdmin = new Admin(username, superAdmin, password);

        AdminService adminService = new AdminService();
        adminService.create(newAdmin);

        resp.sendRedirect(req.getContextPath() + "/SuperAdmin/ListAdmin");
    }
}