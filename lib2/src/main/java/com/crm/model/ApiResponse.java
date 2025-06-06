package com.crm.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private int statusCode;
    private Instant timestamp;
}