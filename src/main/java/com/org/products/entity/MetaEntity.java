package com.org.products.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MetaEntity {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date metaCreatedAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date metaUpdatedAt;

    @Column(name = "barcode")
    private String metaBarcode;

    @Column(name = "qr_code")
    private String metaQrCode;

}
