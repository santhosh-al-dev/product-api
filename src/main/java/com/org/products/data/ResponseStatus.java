package com.org.products.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    private String status;
    private String error;
    private String message;

    public ResponseStatus(String status, String responseMessage) {
        this.status = status;
        this.message = responseMessage;
    }
}
