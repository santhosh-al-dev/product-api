package com.org.products.controller;

import com.org.products.data.BaseResponse;
import com.org.products.data.ProductBaseResponse;
import com.org.products.data.ResponseStatus;
import com.org.products.data.SearchProductRequest;
import com.org.products.data.UpdateProductRequest;
import com.org.products.expectionhandling.ProductNotFoundException;
import com.org.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.org.products.Constants.CATEGORY_NOT_FOUND;
import static com.org.products.Constants.ERROR_PRODUCT_UPDATE;
import static com.org.products.Constants.MSG_ERROR;
import static com.org.products.Constants.MSG_FAILURE;
import static com.org.products.Constants.MSG_SUCCESS;
import static com.org.products.Constants.PRODUCTS_NOT_FOUND;
import static com.org.products.Constants.UPDATE_PRODUCT_BY_ID;

@RestController
@RequestMapping("org/products/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    //get all the products and store in database
    @GetMapping("/get-products")
    public BaseResponse registerEmployee() {
        return productService.getProducts();
    }

    //search product based upon title and description
    @PostMapping("/search-products")
    public ResponseEntity<List<ProductBaseResponse.ProductDataResponse>> searchByProducts(
            @RequestBody SearchProductRequest searchProductRequest) {
        List<ProductBaseResponse.ProductDataResponse> productEntities = productService.searchByProducts(searchProductRequest.getQuery());
        if (productEntities.isEmpty()) {
            throw new ProductNotFoundException(PRODUCTS_NOT_FOUND);
        }
        return ResponseEntity.ok(productEntities);
    }

    //search product by category
    @PostMapping("/search-by-category")
    public ResponseEntity<List<ProductBaseResponse.ProductDataResponse>> searchByCategory(
            @RequestBody SearchProductRequest searchProductRequest) {
        List<ProductBaseResponse.ProductDataResponse> productEntities = productService.searchByCategory(searchProductRequest.getQuery());
        if (productEntities.isEmpty()) {
            throw new ProductNotFoundException(CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok(productEntities);
    }

    //delete product by category
    @PostMapping("/delete-by-category")
    public ResponseEntity<?> deleteProductsByCategory(@RequestBody SearchProductRequest searchProductRequest) {
        String responseMessage = productService.deleteProductsByCategory(searchProductRequest.getQuery());
        return ResponseEntity.ok(new ResponseStatus(MSG_SUCCESS, responseMessage));
    }

    //update product by productId
    @PostMapping("/update-product-by-id")
    public ResponseEntity<ResponseStatus> updateProductTitle(@RequestBody UpdateProductRequest updateProductRequest) {
        try {
            productService.updateProductById(updateProductRequest);
            ResponseStatus responseStatus = new ResponseStatus(MSG_SUCCESS,
                    UPDATE_PRODUCT_BY_ID + updateProductRequest.getId());
            return ResponseEntity.ok(responseStatus);
        } catch (ProductNotFoundException e) {
            ResponseStatus responseStatus = new ResponseStatus(MSG_FAILURE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatus);
        } catch (Exception e) {
            ResponseStatus responseStatus = new ResponseStatus(MSG_ERROR, ERROR_PRODUCT_UPDATE + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseStatus);
        }
    }

    //get recently updated product
    @GetMapping("/get-updated-products/{productId}")
    public ResponseEntity<ProductBaseResponse.ProductDataResponse> getUpdatedProductsById(@PathVariable Integer productId) {
        ProductBaseResponse.ProductDataResponse updatedProductResponse = productService.getUpdatedProducts(productId);
        return ResponseEntity.ok(updatedProductResponse);
    }
}
