package com.m2i.filrougebo.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageService {

    public String getImageAsBase64(String imagePath) throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Image not found in resources: " + imagePath);
            }

            byte[] fileContent = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);
            }
        }

}
