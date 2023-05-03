package com.m2i.filrougebo.servlets.category;

import com.m2i.filrougebo.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = AddCategoryServlet.url)
public class AddCategoryServlet extends HttpServlet {

    private static CategoryService categoryService = new CategoryService();
    static final String url =  "/secured/add-category";
    private static final String jspPath = "WEB-INF/category/add-category.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(jspPath).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        categoryService.create(categoryName);
        // send redirect to the list of categories

    }
}
