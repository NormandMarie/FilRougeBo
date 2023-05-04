package com.m2i.filrougebo.servlet.category;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = EditCategoryServlet.URL)
public class EditCategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();
    public static final String URL =  "/secured/edit-category";
    private static final String JSP = "/WEB-INF/category/edit-category.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the id from value of button clicked in list-category.jsp
        int id = Integer.parseInt(req.getParameter("editBtn"));
        //find the category by id
        Category toUpdateCategory = categoryService.findById(id);
        // set the category as attribute in the request
        req.setAttribute("toUpdateCategory",toUpdateCategory);
        //forward
        req.getRequestDispatcher(JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the updated name
        String updatedCategoryName = req.getParameter("name");
        // get the id from button value
        int idTargetCategory = Integer.parseInt(req.getParameter("id"));
        //get the target category and set the new name
        Category targetCategory = categoryService.findById(idTargetCategory);
        targetCategory.setName(updatedCategoryName);
        //update
        categoryService.update(targetCategory);
        //redirect to list categories
        resp.sendRedirect(ListCategoryServlet.URL);
    }
}
