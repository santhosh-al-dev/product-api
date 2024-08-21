package com.org.products.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productreviews", schema = "products")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "rating", nullable = false)
    private int reviewRating;

    @Column(name = "comment")
    private String reviewComment;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    @Column(name = "reviewer_name")
    private String reviewerName;

    @Column(name = "reviewer_email")
    private String reviewerEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productId;

}
