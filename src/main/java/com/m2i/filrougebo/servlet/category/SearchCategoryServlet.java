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

@WebServlet(urlPatterns = SearchCategoryServlet.URL)
public class SearchCategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();
    public static final String URL =  "/secured/search-category";
    private static final String JSP = "/WEB-INF/category/category-list.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchQuery = req.getParameter("search");

        List<Category> categories = categoryService.searchByName(searchQuery);

        req.setAttribute("categories", categories);
        req.setAttribute("searchQuery", searchQuery);

        req.getRequestDispatcher(JSP).forward(req,resp);

    }
}
