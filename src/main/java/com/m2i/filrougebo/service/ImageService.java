package com.m2i.filrougebo.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageService {

    private static final String FALLBACK_IMAGE_PATH = "/img/product/placeholder-image.jpg";

    public String getImageAsBase64(String imagePath) throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                return getFallbackImageAsBase64();
//                throw new FileNotFoundException("Image not found in resources: " + imagePath);
            }

            byte[] fileContent = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);
            }
    }

    private String getFallbackImageAsBase64() throws IOException {
        try (InputStream fallbackStream = getClass().getResourceAsStream(FALLBACK_IMAGE_PATH)) {
            if (fallbackStream == null) {
                throw new FileNotFoundException("Fallback image not found: " + FALLBACK_IMAGE_PATH);
            }

            byte[] fileContent = fallbackStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);
        }
    }

}
