package com.m2i.filrougebo.servlet.category;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = DeleteCategoryServlet.URL)
public class DeleteCategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();
    public static final String URL = "/secured/delete-category";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get the id from the value of the button clicked (list-categories.jsp)
        int id = Integer.parseInt(req.getParameter("deleteBtn"));
        //then find the categ by Id
        Category categoryToDelete = categoryService.findById(id);
        //then delete it
        categoryService.delete(categoryToDelete);
        // redirect to list-categories
        resp.sendRedirect(ListCategoryServlet.URL);

    }
}
