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
public class ProductMetaResponse {
    @JsonProperty("createdAt")
    private Date metaCreatedAt;

    @JsonProperty("updatedAt")
    private Date metaUpdatedAt;

    @JsonProperty("barcode")
    private String metaBarcode;

    @JsonProperty("qrCode")
    private String metaQrCode;
}
