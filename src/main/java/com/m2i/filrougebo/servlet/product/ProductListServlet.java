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

@WebServlet(urlPatterns = ProductListServlet.URL)
public class ProductListServlet extends HttpServlet {

    public static final String URL = "/secured/list-product";
    public static final String JSP = "/WEB-INF/product/product-list.jsp";
    private static final ImageService imageService = new ImageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductService();
        List<Product> listOfAllProducts = productService.findAll();

        // TODO : should use DTO
        for (Product product : listOfAllProducts) {
                    String base64Image = imageService.getImageAsBase64FromProduct(product);
                    product.setImgUrl(base64Image);
                }

        req.setAttribute("products", listOfAllProducts);

        req.getRequestDispatcher(JSP).forward(req, resp);
    }
}
