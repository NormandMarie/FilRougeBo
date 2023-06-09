package com.m2i.filrougebo.servlet.product;


import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = ProductDeleteServlet.URL)
public class ProductDeleteServlet extends HttpServlet {

    public static final String URL = "/secured/delete-product";
    private static final String JSP = "";

    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);

        productService.deleteProduct(product);

        resp.sendRedirect(ProductListServlet.URL);

    }
}
