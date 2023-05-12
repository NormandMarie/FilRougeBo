package com.m2i.filrougebo.servlet.product;

import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;
import com.m2i.filrougebo.service.MonthService;
import com.m2i.filrougebo.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = ProductDetailsServlet.URL)
public class ProductDetailsServlet extends HttpServlet {

    public static final String URL = "/secured/details-product";
    private static final String JSP = "/WEB-INF/product/product-details.jsp";

    ProductService productService = new ProductService();
    MonthService monthService = new MonthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);

        List<Month> monthList = monthService.findAllAndSort();

        req.setAttribute("product", product);
        req.setAttribute("monthList", monthList);



        req.getRequestDispatcher(ProductDetailsServlet.JSP).forward(req, resp);

    }

}
