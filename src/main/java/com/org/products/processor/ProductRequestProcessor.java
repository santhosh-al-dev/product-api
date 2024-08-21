package com.org.products.processor;

import com.org.products.data.ProductBaseResponse;
import com.org.products.expectionhandling.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductRequestProcessor {
    private final String getProductDetailsUrl;
    RestTemplate getProductDetails;

    private ProductRequestProcessor(@Qualifier("org-products-details") RestTemplate getProductDetails,
                                    @Value("${org.products.api}") String getProductDetailsUrl) {
        this.getProductDetails = getProductDetails;
        this.getProductDetailsUrl = getProductDetailsUrl;
    }


    //process the api response and store in database
    public ProductBaseResponse saveProducts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        return productEntity(getProductDetailsUrl, httpHeaders, ProductBaseResponse.class);
    }

    public <T> T productEntity(String url, HttpHeaders httpHeaders, Class<T> tClass) {
        ResponseEntity<T> responseEntity;
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        try {
            responseEntity = getProductDetails.getForEntity(url, tClass);
        } catch (ProductNotFoundException productNotFoundException) {
            throw productNotFoundException;
        }
        return responseEntity.getBody();
    }

}



