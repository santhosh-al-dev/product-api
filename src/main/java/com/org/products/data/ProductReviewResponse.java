package com.org.products.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductReviewResponse {

    @JsonProperty("rating")
    private int reviewRating;

    @JsonProperty("comment")
    private String reviewComment;

    @JsonProperty("date")
    private Date reviewDate;

    @JsonProperty("reviewerName")
    private String reviewerName;

    @JsonProperty("reviewerEmail")
    private String reviewerEmail;

}
