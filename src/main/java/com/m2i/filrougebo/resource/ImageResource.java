package com.m2i.filrougebo.resource;

import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.service.ImageService;
import com.m2i.filrougebo.service.ProductService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.io.IOException;

@Path("/images")
public class ImageResource {

    private ImageService imageService = new ImageService();
    private ProductService productService = new ProductService();


    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImagePerId(@PathParam("id") int id) {

        String imageBase64 = null;
        Product product = productService.findById(id);
        String imgPath = "/img/product/";
        imgPath += product.getId() + "_" + product.getName().replaceAll(" ", "-");

        try {
//            imageBase64 = imageService.getImageAsBase64(product.getImgUrl());
            imageBase64 = imageService.getImageAsBase64(imgPath);

        } catch (IOException e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }

        return Response
                .ok(imageBase64)
                .build();

    }

}
