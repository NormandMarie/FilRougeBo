package com.m2i.filrougebo.service;

import com.m2i.filrougebo.entity.Product;
import jakarta.servlet.http.Part;

import java.io.*;
import java.net.URLDecoder;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringJoiner;

public class ImageService {

    private static final String FALLBACK_PRODUCT_IMAGE_PATH = "/media/img/product/placeholder.jpg";

    private String removesDiacritics(String inputString) {
        String normalizedString = inputString == null ? null : Normalizer.normalize(inputString, Normalizer.Form.NFKD);
        return normalizedString.replaceAll("\\p{M}", "");
    }

    private String getRelativeImgPathFromProduct(Product product) {

        // Only one image per product, so that it can be erased each time a new one is uploaded
        // The image name is saved as {idProduct}_{product.name}.jpg

        String fileName = product.getId() + "_";
        fileName += removesDiacritics(product.getName()).toLowerCase().replaceAll(" ", "-");
        fileName += ".jpg";
        String pathArr[] = {"media", "img", "product", fileName};

        return String.join(File.separator, pathArr);
    }

    public void saveProductImage(Part part, Product product) {

        String pathToUpload = getPathToSource() + File.separator + getRelativeImgPathFromProduct(product);

        try {
            part.write(pathToUpload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get full path of the project source, this allows to fake a ftp
    // Should work only in IntelliJ+TomCat configuration
    public String getPathToSource() {

        // Get target files path
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = null;
        try {
            fullPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String fullPathArr[] = fullPath.split("/");

        int newLength = fullPathArr.length - 4;
        String[] arrPath = new String[newLength];

        System.arraycopy(fullPathArr, 0, arrPath, 0, newLength);

        StringJoiner joiner = new StringJoiner("/");

        for (String element : arrPath) {
            joiner.add(element);
        }

        return new File(joiner.toString()).getPath();
    }

    public String getImageAsBase64FromProduct(Product product) throws IOException {

        String fullPathToSource = getPathToSource() + File.separator + getRelativeImgPathFromProduct(product);

        try (FileInputStream inputStream = new FileInputStream(fullPathToSource)) {

            byte[] fileContent = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);

        } catch (FileNotFoundException e) {
            return getFallbackImageAsBase64(product);
        }

    }

    private String getFallbackImageAsBase64(Object entity) throws IOException {

        if (entity instanceof Product) {
            try (FileInputStream fallbackStream = new FileInputStream(
                    getPathToSource() + FALLBACK_PRODUCT_IMAGE_PATH)) {
                byte[] fileContent = fallbackStream.readAllBytes();
                return Base64.getEncoder().encodeToString(fileContent);
            }
        }
        return null;
    }

}
