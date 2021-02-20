package com.collabera.hcp.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@ApiModel(description = "Error Response Model")
public class ApiResponse {
    private String message;
}
