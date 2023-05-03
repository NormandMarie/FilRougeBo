package com.m2i.filrougebo.servlet.Admin;

import com.m2i.filrougebo.Dto.AdminDto;
import com.m2i.filrougebo.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/SuperAdmin/ListAdmin")
public class ListAdminServlet  extends HttpServlet {
    AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AdminDto> AdminDtos = adminService.getAllAdmins();
        System.out.println(AdminDtos);
        req.setAttribute("AdminDtos", AdminDtos);
        req.getRequestDispatcher("/WEB-INF/admin/ListAdmin.jsp").forward(req, resp);

    }
}
