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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = ProductEditServlet.URL)
@MultipartConfig
public class ProductEditServlet extends HttpServlet {

    public static final String URL = "/secured/edit-product";
    private static final String JSP = "/WEB-INF/product/product-form.jsp";

    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    MonthService monthService = new MonthService();
    ImageService imageService = new ImageService();

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
        double pricePerUnit = Double.parseDouble(req.getParameter("pricePerUnit"));
        //String imgUrl = req.getParameter("imgUrl");
        String imgUrl = "";
        double vat = Double.parseDouble(req.getParameter("vat"));
        String description = req.getParameter("description");
        double stock = Double.parseDouble(req.getParameter("stock"));
        Category category = categoryService.findById(Integer.parseInt(req.getParameter("category")));

        List<Month> seasonalMonths = new ArrayList<>();
        String[] months = req.getParameterValues("months");
        for(String s :months) {
            seasonalMonths.add(Month.valueOf(s.toUpperCase()));
        }


        //TODO: check arguments
        if ( name.isBlank() ) {
            req.setAttribute("editError", "Empty name is not allowed");
        }

        boolean success = productService.updateProduct(
                id, name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);

        Part filePart = req.getPart("imageFile");
        imageService.saveProductImage(filePart, productService.findById(id));

        if (!success) {
            req.setAttribute("editError", "Error while editing product.");
        }

        resp.sendRedirect(ProductListServlet.URL);

    }

}
