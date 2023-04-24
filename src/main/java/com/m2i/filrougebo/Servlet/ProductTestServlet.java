package com.m2i.filrougebo.Servlet;

import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ProductTestServlet.URL)
public class ProductTestServlet extends HttpServlet {

    public static final String URL = "/";
    public static final String JSP = "/WEB-INF/product-test.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductService();
        List<Product> listOfAllProducts = productService.findAll();

        System.out.println(listOfAllProducts);

        req.setAttribute("products", listOfAllProducts);

        req.getRequestDispatcher(JSP).forward(req, resp);
    }
}
