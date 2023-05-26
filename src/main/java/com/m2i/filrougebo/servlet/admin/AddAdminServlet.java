package com.m2i.filrougebo.servlet.admin;

import com.m2i.filrougebo.dto.AdminDto;
import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = AddAdminServlet.URL)
public class AddAdminServlet extends HttpServlet {

    public static final String URL = "/SuperAdmin/add-admin";
    private static final String JSP = "/WEB-INF/admin/AddAdmin.jsp";
    private AdminService adminService = new AdminService();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email    = req.getParameter("email");

        boolean superAdmin  = Boolean.parseBoolean(req.getParameter("superAdmin"));

        Admin newAdmin = new Admin(username, superAdmin, password, firstName, lastName, email);

        Set<ConstraintViolation<Admin>> violations = validator.validate(newAdmin);

        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();

            for (ConstraintViolation<Admin> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(propertyPath, message);
            }

            req.setAttribute("errors", errors);
            req.setAttribute("AdminDtos",adminService.getAllAdmins());

            req.getRequestDispatcher("/WEB-INF/admin/ListAdmin.jsp").forward(req, resp);

        } else {

            adminService.create(newAdmin);
            resp.sendRedirect(ListAdminServlet.URL);
        }
    }
}

