package com.org.products.service;

import com.org.products.data.BaseResponse;
import com.org.products.data.ProductBaseResponse;
import com.org.products.data.UpdateProductRequest;
import com.org.products.entity.ProductEntity;
import com.org.products.expectionhandling.ProductNotFoundException;
import com.org.products.processor.ProductRequestProcessor;
import com.org.products.repository.ProductRepository;
import com.org.products.model.ProductDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.org.products.Constants.DATABASE_STORAGE_ERROR;
import static com.org.products.Constants.ERROR_PRODUCT_DELETE;
import static com.org.products.Constants.GET_PRODUCTS;
import static com.org.products.Constants.MSG_FAILURE;
import static com.org.products.Constants.MSG_SUCCESS;
import static com.org.products.Constants.PRODUCT_NOT_FOUND;
import static com.org.products.Constants.SOMETHING_WENT_WRONG;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRequestProcessor productRequestProcessor;

    private final ProductDTO productDTO;

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRequestProcessor productRequestProcessor, ProductDTO productDTO,
                              ProductRepository productRepository) {
        this.productRequestProcessor = productRequestProcessor;
        this.productDTO = productDTO;
        this.productRepository = productRepository;
    }

    //get products and store in database
    @Override
    public BaseResponse getProducts() {
        try {
            ProductBaseResponse productBaseResponse = productRequestProcessor.saveProducts();

            //save api response data into database
            List<ProductBaseResponse.ProductDataResponse> productDataResponse = productBaseResponse.getGetProductsData();
            // Save all product details and get the saved entities
            List<ProductEntity> savedEntities = saveAllProducts(productDataResponse);
            logger.debug(" save product details into DB: {}", savedEntities);

            return BaseResponse.builder()
                    .status(MSG_SUCCESS)
                    .message(GET_PRODUCTS)
                    .data(productBaseResponse)
                    .build();
        } catch (ProductNotFoundException e) {
            return BaseResponse.builder()
                    .status(MSG_FAILURE)
                    .message(SOMETHING_WENT_WRONG)
                    .data(null)
                    .build();
        }
    }

    //search products by title and description
    @Override
    public List<ProductBaseResponse.ProductDataResponse> searchByProducts(String query) {
        List<ProductEntity> productEntities = productRepository.searchByTitleOrDescription(query);
        return productDTO.convertToProductDtoList(productEntities); // Convert entities to DTOs
    }

    //search products by category
    @Override
    public List<ProductBaseResponse.ProductDataResponse> searchByCategory(String query) {
        List<ProductEntity> productEntities = productRepository.searchByCategory(query);
        return productDTO.convertToProductDtoList(productEntities); // Convert entities to DTOs
    }

    //delete product by category
    @Override
    public String deleteProductsByCategory(String category) {

        long productCount = productRepository.countByCategory(category);

        // If no products found, throw an exception
        if (productCount == 0) {
            throw new ProductNotFoundException(ERROR_PRODUCT_DELETE);
        }
        // Delete product reviews first associated with products in the given category else gives FK constraint violates
        productRepository.deleteReviewsByCategory(category);

        //delete data based upon category
        productRepository.deleteByCategory(category);

        return "Products in category '" + category + "' have been deleted successfully.";
    }

    @Transactional
    public void updateProductById(UpdateProductRequest updateProductRequest) {
        Optional<ProductEntity> product = productRepository.findById(Math.toIntExact(updateProductRequest.getId()));

        if (product.isPresent()) {
            productRepository.updateProductTitleById(updateProductRequest.getId(), updateProductRequest.getTitle());
        } else {
            throw new ProductNotFoundException("Product with ID " + updateProductRequest.getId() + " not found. Please Try Again!");
        }
    }

    @Override
    public ProductBaseResponse.ProductDataResponse getUpdatedProducts(Integer productId) {
        ProductEntity updatedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + productId));
        return productDTO.entityToProductDTO(updatedProduct);
    }

    //Process the conversion of DTO in the API response.
    @Transactional
    public List<ProductEntity> saveAllProducts(List<ProductBaseResponse.ProductDataResponse> productDataResponse) {
        try {
            List<ProductEntity> employeeEntities = productDTO.convertToProductEntityList(productDataResponse);
            logger.debug("Entity before save: {}", employeeEntities);
            return productRepository.saveAll(employeeEntities);
        } catch (Exception e) {
            throw new ProductNotFoundException(DATABASE_STORAGE_ERROR);
        }
    }


}
