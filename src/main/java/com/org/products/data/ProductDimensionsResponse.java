package com.org.products.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDimensionsResponse {

    @JsonProperty("width")
    private double dimensionWidth;

    @JsonProperty("height")
    private double dimensionHeight;

    @JsonProperty("depth")
    private double dimensionDepth;
}
