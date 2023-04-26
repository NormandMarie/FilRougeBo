package com.m2i.filrougebo.Servlet.Product;


import com.m2i.filrougebo.dao.CategoryDao;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;
import com.m2i.filrougebo.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = ProductDeleteServlet.URL)
public class ProductDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-product";
    private static final String JSP = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductService();
        IntCategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(10);
        List<Month> months = new ArrayList<>();

        months.add(Month.APRIL);
        months.add(Month.MAY);
        months.add(Month.JUNE);

        Product product = new Product(18, "Tomatoes", "kg", 1.0D, "imgURL", 5, "description", 1, category, months);

        productService.deleteProduct(product);

        resp.sendRedirect(ProductListServlet.URL);

    }
}
