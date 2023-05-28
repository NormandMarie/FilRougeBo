package com.m2i.filrougebo.servlet.category;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
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

@WebServlet(urlPatterns = AddCategoryServlet.URL)
public class AddCategoryServlet extends HttpServlet {

    private static CategoryService categoryService = new CategoryService();
    public static final String URL =  "/secured/add-category";
    private static final String JSP = "/WEB-INF/category/category-form.jsp";
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("name");
        Category category = new Category(categoryName);
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();

            for (ConstraintViolation<Category> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(propertyPath, message);
            }
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(JSP).forward(req, resp);

        } else {
            categoryService.create(categoryName);
            resp.sendRedirect(ListCategoryServlet.URL);
        }
    }
}
