package com.m2i.filrougebo.servlet.product;

import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.service.ImageService;
import com.m2i.filrougebo.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ProductSearchServlet.URL)
public class ProductSearchServlet extends HttpServlet {

    public static final String URL = "/secured/search-product";
    private static final String JSP = "/WEB-INF/product/product-list.jsp";
    private static final ProductService productService = new ProductService();
    private static final ImageService imageService = new ImageService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchQuery = req.getParameter("search");
        List<Product> productList = productService.searchProductPerNameOrDescriptionOrCategoryName(searchQuery);

        // TODO : should use DTO
        for (Product product : productList) {
            String base64Image = imageService.getImageAsBase64FromProduct(product);
            product.setImgUrl(base64Image);
        }

        req.setAttribute("searchQuery", searchQuery);
        req.setAttribute("products", productList);
        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
