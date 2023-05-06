package com.m2i.filrougebo.servlet.admin;

import com.m2i.filrougebo.dto.AdminDto;
import com.m2i.filrougebo.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ListAdminServlet.URL)
public class ListAdminServlet extends HttpServlet {

    public static final String URL = "/SuperAdmin/ListAdmin";
    private static final String JSP = "/WEB-INF/admin/ListAdmin.jsp";
    AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<AdminDto> AdminDtos = adminService.getAllAdmins();

        System.out.println(AdminDtos);

        req.setAttribute("AdminDtos", AdminDtos);
        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
