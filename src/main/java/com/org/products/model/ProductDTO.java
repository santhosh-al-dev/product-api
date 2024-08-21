package com.org.products.model;

import com.org.products.data.ProductBaseResponse;
import com.org.products.data.ProductDimensionsResponse;
import com.org.products.data.ProductMetaResponse;
import com.org.products.data.ProductReviewResponse;
import com.org.products.entity.DimensionsEntity;
import com.org.products.entity.MetaEntity;
import com.org.products.entity.ProductEntity;
import com.org.products.entity.ReviewEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDTO {

    // Method to convert a list of Product DTO to a list of Product Entity
    public List<ProductEntity> convertToProductEntityList(List<ProductBaseResponse.ProductDataResponse> productDataResponses) {
        return productDataResponses.stream()
                .map(this::productsDtoToEntity)
                .collect(Collectors.toList());
    }

    public ProductEntity productsDtoToEntity(ProductBaseResponse.ProductDataResponse productDataResponse) {
        ProductEntity productEntity = new ProductEntity();
        // If product id is zero, let the database auto generate it
        if (productDataResponse.getProductId() != 0) {
            productEntity.setProductId(productDataResponse.getProductId());
        }

        productEntity.setProductTitle(productDataResponse.getProductTitle());
        productEntity.setProductDescription(productDataResponse.getProductDescription());
        productEntity.setProductCategory(productDataResponse.getProductCategory());
        productEntity.setProductPrice(productDataResponse.getProductPrice());
        productEntity.setProductDiscountPercentage(productDataResponse.getProductDiscountPercentage());
        productEntity.setProductRating(productDataResponse.getProductRating());
        productEntity.setProductStock(productDataResponse.getProductStock());
        productEntity.setProductTags(productDataResponse.getProductTags());
        productEntity.setProductBrand(productDataResponse.getProductBrand());
        productEntity.setProductSku(productDataResponse.getProductSku());
        productEntity.setProductWeight(productDataResponse.getProductWeight());

        productEntity.setDimensionsEntity(productDimensionDTOToEntity(productDataResponse.getProductDimensionsResponse()));
        productEntity.setProductWarrantyInformation(productDataResponse.getProductWarrantyInformation());
        productEntity.setProductShippingInformation(productDataResponse.getProductShippingInformation());
        productEntity.setProductAvailabilityStatus(productDataResponse.getProductAvailabilityStatus());

        productEntity.setProductReviewsEntity(
                productDataResponse.getProductReviews().stream()
                        .map(reviewResponse -> productReviewDTOToEntity(reviewResponse, productEntity))
                        .collect(Collectors.toList())
        );

        productEntity.setProductReturnPolicy(productDataResponse.getProductReturnPolicy());
        productEntity.setProductMinimumOrderQuantity(productDataResponse.getProductMinimumOrderQuantity());
        productEntity.setMetaEntity(productMetaDTOToEntity(productDataResponse.getProductMeta()));
        productEntity.setProductImages(productDataResponse.getProductImages());
        productEntity.setProductThumbnail(productDataResponse.getProductThumbnail());

        return productEntity;
    }


    public DimensionsEntity productDimensionDTOToEntity(ProductDimensionsResponse productDimensionResponse) {
        DimensionsEntity dimensionsEntity = new DimensionsEntity();
        dimensionsEntity.setDimensionWidth(productDimensionResponse.getDimensionWidth());
        dimensionsEntity.setDimensionHeight(productDimensionResponse.getDimensionHeight());
        dimensionsEntity.setDimensionDepth(productDimensionResponse.getDimensionDepth());

        return dimensionsEntity;
    }

    public ReviewEntity productReviewDTOToEntity(ProductReviewResponse productReviewResponse, ProductEntity productEntity) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewRating(productReviewResponse.getReviewRating());
        reviewEntity.setReviewComment(productReviewResponse.getReviewComment());
        reviewEntity.setReviewDate(productReviewResponse.getReviewDate());
        reviewEntity.setReviewerName(productReviewResponse.getReviewerName());
        reviewEntity.setReviewerEmail(productReviewResponse.getReviewerEmail());
        reviewEntity.setProductId(productEntity); //foreign key mapping
        return reviewEntity;
    }

    public MetaEntity productMetaDTOToEntity(ProductMetaResponse productMetaResponse) {
        MetaEntity metaEntity = new MetaEntity();
        metaEntity.setMetaCreatedAt(productMetaResponse.getMetaCreatedAt());
        metaEntity.setMetaUpdatedAt(productMetaResponse.getMetaUpdatedAt());
        metaEntity.setMetaBarcode(productMetaResponse.getMetaBarcode());
        metaEntity.setMetaQrCode(productMetaResponse.getMetaQrCode());

        return metaEntity;
    }

    // Method to convert a list of Entity Product to a list of Product DTO

    public List<ProductBaseResponse.ProductDataResponse> convertToProductDtoList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::entityToProductDTO)
                .collect(Collectors.toList());
    }


    public ProductBaseResponse.ProductDataResponse entityToProductDTO(ProductEntity productEntity) {
        ProductBaseResponse.ProductDataResponse productDataResponse = new ProductBaseResponse.ProductDataResponse();
        productDataResponse.setProductId(productEntity.getProductId());
        productDataResponse.setProductTitle(productEntity.getProductTitle());
        productDataResponse.setProductDescription(productEntity.getProductDescription());
        productDataResponse.setProductCategory(productEntity.getProductCategory());
        productDataResponse.setProductPrice(productEntity.getProductPrice());
        productDataResponse.setProductDiscountPercentage(productEntity.getProductDiscountPercentage());
        productDataResponse.setProductRating(productEntity.getProductRating());
        productDataResponse.setProductStock(productEntity.getProductStock());
        productDataResponse.setProductTags(productEntity.getProductTags());
        productDataResponse.setProductBrand(productEntity.getProductBrand());
        productDataResponse.setProductSku(productEntity.getProductSku());
        productDataResponse.setProductWeight(productEntity.getProductWeight());
        productDataResponse.setProductDimensionsResponse(entityToProductDimensionDTO(productEntity.getDimensionsEntity()));
        productDataResponse.setProductWarrantyInformation(productEntity.getProductWarrantyInformation());
        productDataResponse.setProductShippingInformation(productEntity.getProductShippingInformation());
        productDataResponse.setProductAvailabilityStatus(productEntity.getProductAvailabilityStatus());
        productDataResponse.setProductReviews(productEntity.getProductReviewsEntity().stream()
                .map(this::entityToProductReviewDTO)
                .collect(Collectors.toList())
        );
        productDataResponse.setProductReturnPolicy(productEntity.getProductReturnPolicy());
        productDataResponse.setProductMinimumOrderQuantity(productEntity.getProductMinimumOrderQuantity());
        productDataResponse.setProductMeta(entityToProductMetaDTO(productEntity.getMetaEntity()));
        productDataResponse.setProductImages(productEntity.getProductImages());
        productDataResponse.setProductThumbnail(productEntity.getProductThumbnail());

        return productDataResponse;
    }

    public ProductDimensionsResponse entityToProductDimensionDTO(DimensionsEntity dimensionsEntity) {
        ProductDimensionsResponse productDimensionsResponse = new ProductDimensionsResponse();
        productDimensionsResponse.setDimensionWidth(dimensionsEntity.getDimensionWidth());
        productDimensionsResponse.setDimensionHeight(dimensionsEntity.getDimensionHeight());
        productDimensionsResponse.setDimensionDepth(dimensionsEntity.getDimensionDepth());

        return productDimensionsResponse;
    }


    public ProductReviewResponse entityToProductReviewDTO(ReviewEntity reviewEntity) {
        ProductReviewResponse productReviewResponse = new ProductReviewResponse();
        productReviewResponse.setReviewRating(reviewEntity.getReviewRating());
        productReviewResponse.setReviewComment(reviewEntity.getReviewComment());
        productReviewResponse.setReviewDate(reviewEntity.getReviewDate());
        productReviewResponse.setReviewerName(reviewEntity.getReviewerName());
        productReviewResponse.setReviewerEmail(reviewEntity.getReviewerEmail());

        // Assuming you want to set the product's ID or other product-related details in the response DTO:
        // productReviewResponse.setProductId(reviewEntity.getProduct().getId()); // example if needed

        return productReviewResponse;
    }

    public ProductMetaResponse entityToProductMetaDTO(MetaEntity metaEntity) {
        ProductMetaResponse productMetaResponse = new ProductMetaResponse();
        productMetaResponse.setMetaCreatedAt(metaEntity.getMetaCreatedAt());
        productMetaResponse.setMetaUpdatedAt(metaEntity.getMetaUpdatedAt());
        productMetaResponse.setMetaBarcode(metaEntity.getMetaBarcode());
        productMetaResponse.setMetaQrCode(metaEntity.getMetaQrCode());

        return productMetaResponse;
    }


}
