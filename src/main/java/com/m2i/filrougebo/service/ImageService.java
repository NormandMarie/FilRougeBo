package com.m2i.filrougebo.service;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringJoiner;

public class ImageService {

    private static final String FALLBACK_PRODUCT_IMAGE_PATH = "media/img/product/placeholder.jpg";

    // Get full path of the project source, this allows to fake a ftp server
    public String getPathToSource(String imagePath) throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String fullPathArr[] = fullPath.split("/");

        int newLength = fullPathArr.length - 4;
        String[] arrPath = new String[newLength];

        System.arraycopy(fullPathArr, 0, arrPath, 0, newLength);

        StringJoiner joiner = new StringJoiner("/");

        for (String element : arrPath) {
            joiner.add(element);
        }

        String responsePath = null;
        if (!imagePath.startsWith("/")) {
            responsePath = new File(joiner.toString()).getPath() + File.separatorChar + imagePath;
        } else {
            responsePath = new File(joiner.toString()).getPath()  + imagePath;
        }

        return responsePath;
    }


    public String getImageAsBase64(String imagePath) throws IOException {

        try (FileInputStream inputStream = new FileInputStream(getPathToSource(imagePath))) {

            byte[] fileContent = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);

        } catch (FileNotFoundException e) {
            return getFallbackImageAsBase64();
        }

    }

    private String getFallbackImageAsBase64() throws IOException {

        try (FileInputStream fallbackStream = new FileInputStream(
                getPathToSource(FALLBACK_PRODUCT_IMAGE_PATH))) {
            if (fallbackStream == null) {
                throw new FileNotFoundException("Fallback image not found");
            }

            byte[] fileContent = fallbackStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);
        }
    }

}
