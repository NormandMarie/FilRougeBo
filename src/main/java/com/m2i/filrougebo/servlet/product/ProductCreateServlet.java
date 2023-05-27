package com.m2i.filrougebo.servlet.product;

import com.m2i.filrougebo.dao.CategoryDao;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Admin;
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

@WebServlet(urlPatterns = ProductCreateServlet.URL)
@MultipartConfig
public class ProductCreateServlet extends HttpServlet {

    public static final String URL = "/secured/add-product";
    private static final String JSP = "/WEB-INF/product/product-form.jsp";

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private MonthService monthService = new MonthService();
    private ImageService imageService = new ImageService();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categoryList = categoryService.findAll();
        List<Month> monthList = monthService.findAllAndSort();

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("monthList", monthList);

        req.getRequestDispatcher(ProductCreateServlet.JSP).forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String unit = req.getParameter("unit");
//        String imgUrl = req.getParameter("imgUrl");
        String imgUrl = "";
        Part filePart = req.getPart("imageFile");

        //if(req.getParameter("pricePerUnit")==null && req.getParameter("pricePerUnit").isEmpty()
        //            || req.getParameter("vat")==null && req.getParameter("vat").isEmpty()
        //                || req.getParameter("stock")==null && req.getParameter("stock").isEmpty()){
        //
        //            req.setAttribute("createError", "Price, vat, stock must not be empty");
        //        }

        double pricePerUnit = Double.parseDouble(req.getParameter("pricePerUnit"));
        double vat = Double.parseDouble(req.getParameter("vat"));
        double stock = Double.parseDouble(req.getParameter("stock"));

        String description = req.getParameter("description");

        Category category = categoryService.findById(Integer.parseInt(req.getParameter("category")));

        List<Month> seasonalMonths = new ArrayList<>();
        String[] months = req.getParameterValues("months");

        for (String s : months) {
            seasonalMonths.add(Month.valueOf(s.toUpperCase()));
            System.out.println(Month.valueOf(s.toUpperCase()));

        }

        Product product = new Product(name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();

            for (ConstraintViolation<Product> violation : violations) {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(propertyPath, message);
            }


            req.setAttribute("errors", errors);
            req.getRequestDispatcher(JSP).forward(req, resp);
        } else {

            Product newProduct = productService.addProduct(product);
            imageService.saveProductImage(filePart, product);

            if (newProduct.getId()==0) {
                req.setAttribute("createError", "Error while creating product.");
            }
            resp.sendRedirect(ProductListServlet.URL);
        }
    }
}
