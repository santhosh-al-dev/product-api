package com.org.products.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productdetails", schema = "products")
public class ProductEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "title", nullable = false)
    private String productTitle;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "category")
    private String productCategory;

    @Column(name = "price", nullable = false)
    private double productPrice;

    @Column(name = "discount_percentage")
    private double productDiscountPercentage;

    @Column(name = "rating")
    private double productRating;

    @Column(name = "stock")
    private int productStock;

    @Column(name = "brand")
    private String productBrand;

    @Column(name = "sku")
    private String productSku;

    @Column(name = "weight")
    private double productWeight;

    @Column(name = "warranty_information")
    private String productWarrantyInformation;

    @Column(name = "shipping_information")
    private String productShippingInformation;

    @Column(name = "availability_status")
    private String productAvailabilityStatus;

    @Column(name = "return_policy")
    private String productReturnPolicy;

    @Column(name = "minimum_order_quantity")
    private int productMinimumOrderQuantity;

    @Column(name = "thumbnail")
    private String productThumbnail;

    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> productTags;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image")
    private List<String> productImages;

    @Embedded
    private DimensionsEntity dimensionsEntity;

    @Embedded
    private MetaEntity metaEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productId")
    private List<ReviewEntity> productReviewsEntity;

}

