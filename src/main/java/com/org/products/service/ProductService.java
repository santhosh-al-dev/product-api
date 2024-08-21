package com.org.products.service;

import com.org.products.data.BaseResponse;
import com.org.products.data.ProductBaseResponse;
import com.org.products.data.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    BaseResponse getProducts();

    List<ProductBaseResponse.ProductDataResponse> searchByProducts(String query);

    List<ProductBaseResponse.ProductDataResponse> searchByCategory(String query);

    String deleteProductsByCategory(String query);

    void updateProductById(UpdateProductRequest updateProductRequest);

    ProductBaseResponse.ProductDataResponse getUpdatedProducts(Integer productId);
}
