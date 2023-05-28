package com.m2i.filrougebo.servlet.product;

import com.m2i.filrougebo.dao.CategoryDao;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;
import com.m2i.filrougebo.service.CategoryService;
import com.m2i.filrougebo.service.ImageService;
import com.m2i.filrougebo.service.MonthService;
import com.m2i.filrougebo.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = ProductEditServlet.URL)
@MultipartConfig
public class ProductEditServlet extends HttpServlet {

    public static final String URL = "/secured/edit-product";
    private static final String JSP = "/WEB-INF/product/product-form.jsp";
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private MonthService monthService = new MonthService();
    private ImageService imageService = new ImageService();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);

        List<Category> categoryList = categoryService.findAllCategoriesExceptProductCategory(product);
        List<Month> monthList = monthService.findAllAndSort();
        // TODO: should use DTO object instead
        product.setImgUrl(imageService.getImageAsBase64FromProduct(product));

        req.setAttribute("product", product);
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("monthList", monthList);

        req.getRequestDispatcher(ProductEditServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String unit = req.getParameter("unit");
        String description = req.getParameter("description");

        double pricePerUnit = Double.parseDouble(req.getParameter("pricePerUnit"));
        double vat = Double.parseDouble(req.getParameter("vat"));
        double stock = Double.parseDouble(req.getParameter("stock"));

        String imgUrl = productService.findById(id).getImgUrl();

        Category category = categoryService.findById(Integer.parseInt(req.getParameter("category")));

        List<Month> seasonalMonths = new ArrayList<>();

        String[] months = req.getParameterValues("months");
        for(String s :months) {
            seasonalMonths.add(Month.valueOf(s.toUpperCase()));
        }

        Product product = new Product(id,name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();

            for (ConstraintViolation<Product> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(propertyPath, message);
            }
            Part filePart = req.getPart("imageFile");

            if(filePart.getSize()>0){
                imageService.saveProductImage(filePart, product);
            }

            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
            req.getRequestDispatcher(JSP).forward(req, resp);

        } else {

            Part filePart = req.getPart("imageFile");
            if(filePart.getSize()>0){
                imageService.saveProductImage(filePart, productService.findById(id));
                resp.sendRedirect(ProductListServlet.URL);
            } else{
                boolean isEdited = productService.editProduct(product);
                if (!isEdited) {
                    req.setAttribute("editError", "Error while editing product.");
                }
                resp.sendRedirect(ProductListServlet.URL);
            }
        }
    }
}
