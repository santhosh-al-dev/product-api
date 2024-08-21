package com.org.products.repository;

import com.org.products.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //search query based upon title or description
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.productTitle) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.productDescription) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ProductEntity> searchByTitleOrDescription(@Param("query") String query);


    //search query product by category
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.productCategory) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ProductEntity> searchByCategory(@Param("query") String query);


    //query delete product by category
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductEntity p WHERE LOWER(p.productCategory) = LOWER(:productCategory)")
    void deleteByCategory(@Param("productCategory") String query);

    @Query("SELECT COUNT(p) FROM ProductEntity p WHERE LOWER(p.productCategory) = LOWER(:productCategory)")
    long countByCategory(@Param("productCategory") String productCategory);


    //delete review table first before deleting product table because review table fk associated
    @Modifying
    @Transactional
    @Query("DELETE FROM ReviewEntity pr WHERE pr.productId IN (SELECT p FROM ProductEntity p " +
            "WHERE LOWER(p.productCategory) = LOWER(:productCategory))")
    void deleteReviewsByCategory(@Param("productCategory") String category);


    //update query by product id and title
    @Modifying
    @Query("UPDATE ProductEntity p SET p.productTitle = :title WHERE p.productId = :id")
    void updateProductTitleById(@Param("id") Long id, @Param("title") String title);
}


