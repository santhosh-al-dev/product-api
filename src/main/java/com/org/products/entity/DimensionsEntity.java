package com.org.products.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DimensionsEntity {

    @Column(name = "width")
    private double dimensionWidth;

    @Column(name = "height")
    private double dimensionHeight;

    @Column(name = "depth")
    private double dimensionDepth;
}



