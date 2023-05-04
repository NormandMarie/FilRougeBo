package com.m2i.filrougebo.servlet.category;

import com.m2i.filrougebo.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = AddCategoryServlet.URL)
public class AddCategoryServlet extends HttpServlet {

    private static CategoryService categoryService = new CategoryService();
    public static final String URL =  "/secured/add-category";
    private static final String JSP = "/WEB-INF/category/add-category.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        categoryService.create(categoryName);
        resp.sendRedirect(ListCategoryServlet.URL);
    }
}
