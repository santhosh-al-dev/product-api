package com.org.products.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //ignore any unknown field if newly added
public class ProductBaseResponse {

    @JsonProperty("products")
    private List<ProductDataResponse> getProductsData;

    @JsonProperty("total")
    private int total;

    @JsonProperty("skip")
    private int skip;

    @JsonProperty("limit")
    private int limit;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductDataResponse {
        @JsonProperty("id")
        private int productId;

        @JsonProperty("title")
        private String productTitle;

        @JsonProperty("description")
        private String productDescription;

        @JsonProperty("category")
        private String productCategory;

        @JsonProperty("price")
        private double productPrice;

        @JsonProperty("discountPercentage")
        private double productDiscountPercentage;

        @JsonProperty("rating")
        private double productRating;

        @JsonProperty("stock")
        private int productStock;

        @JsonProperty("tags")
        private List<String> productTags;

        @JsonProperty("brand")
        private String productBrand;

        @JsonProperty("sku")
        private String productSku;

        @JsonProperty("weight")
        private double productWeight;

        @JsonProperty("dimensions")
        private ProductDimensionsResponse productDimensionsResponse;

        @JsonProperty("warrantyInformation")
        private String productWarrantyInformation;

        @JsonProperty("shippingInformation")
        private String productShippingInformation;

        @JsonProperty("availabilityStatus")
        private String productAvailabilityStatus;

        @JsonProperty("reviews")
        private List<ProductReviewResponse> productReviews;

        @JsonProperty("returnPolicy")
        private String productReturnPolicy;

        @JsonProperty("minimumOrderQuantity")
        private int productMinimumOrderQuantity;

        @JsonProperty("meta")
        private ProductMetaResponse productMeta;

        @JsonProperty("images")
        private List<String> productImages;

        @JsonProperty("thumbnail")
        private String productThumbnail;

    }

}