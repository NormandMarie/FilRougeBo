package com.m2i.filrougebo.servlet.category;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = EditCategoryServlet.URL)
public class EditCategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();
    public static final String URL =  "/secured/edit-category";
    private static final String JSP = "/WEB-INF/category/category-form.jsp";
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the id from value of button clicked in category-list.jsp
        int id = Integer.parseInt(req.getParameter("id"));

        //find the category by id
        Category toUpdateCategory = categoryService.findById(id);

        // set the category as attribute in the request
        req.setAttribute("category", toUpdateCategory);
        //forward
        req.getRequestDispatcher(JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the updated name
        int idTargetCategory = Integer.parseInt(req.getParameter("id"));
        String updatedCategoryName = req.getParameter("name");
        Category updatedCategory = new Category(idTargetCategory,updatedCategoryName);

        Category targetCategory = categoryService.findById(idTargetCategory);

        Set<ConstraintViolation<Category>> violations = validator.validate(updatedCategory);

        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();

            for (ConstraintViolation<Category> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(propertyPath, message);
            }
            req.setAttribute("errors", errors);
            req.setAttribute("category",updatedCategory);
            req.getRequestDispatcher(JSP).forward(req, resp);

        } else {
            categoryService.update(updatedCategory);
            resp.sendRedirect(ListCategoryServlet.URL);
        }
    }
}
