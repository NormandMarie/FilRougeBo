package com.m2i.filrougebo.servlet.category;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ListCategoryServlet.URL)
public class ListCategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();
    public static final String URL =  "/secured/list-category";
    private static final String JSP = "/WEB-INF/category/category-list.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher(JSP).forward(req,resp);

    }
}
