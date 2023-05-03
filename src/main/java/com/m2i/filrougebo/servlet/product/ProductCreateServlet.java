package com.m2i.filrougebo.servlet.product;

import com.m2i.filrougebo.dao.CategoryDao;
import com.m2i.filrougebo.dao.IntCategoryDao;
import com.m2i.filrougebo.entity.Category;
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

@WebServlet(urlPatterns = ProductCreateServlet.URL)
public class ProductCreateServlet extends HttpServlet {

    public static final String URL = "/create-product";
    private static final String JSP = "/WEB-INF/product/product-form.jsp";

    ProductService productService = new ProductService();

    //TODO: Change to Service
    IntCategoryDao categoryDao = new CategoryDao();

    MonthService monthService = new MonthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categoryList = categoryDao.findAll();
        List<Month> monthList = monthService.findAll();

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("monthList", monthList);

        req.getRequestDispatcher(ProductCreateServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String unit = req.getParameter("unit");
        double pricePerUnit = Double.parseDouble(req.getParameter("pricePerUnit"));
        String imgUrl = req.getParameter("imgUrl");
        double vat = Double.parseDouble(req.getParameter("vat"));
        String description = req.getParameter("description");
        double stock = Double.parseDouble(req.getParameter("stock"));
        Category category = categoryDao.findById(Integer.valueOf(req.getParameter("category")));

        List<Month> seasonalMonths = new ArrayList<>();
        String[] months = req.getParameterValues("months");
        for(String s :months) {
            seasonalMonths.add(Month.valueOf(s.toUpperCase()));
            System.out.println(Month.valueOf(s.toUpperCase()));
        }


        //TODO: check arguments
        if ( name.isBlank() ) {
            req.setAttribute("createError", "Empty name is not allowed");
        }

        Product product = productService.createProduct(
                name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);

        if (product == null) {
            req.setAttribute("createError", "Error while creating product.");
        }

        resp.sendRedirect(ProductListServlet.URL);

    }

}
