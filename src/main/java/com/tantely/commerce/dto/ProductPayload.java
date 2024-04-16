package com.tantely.commerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductPayload(
    Long id,
    String name,
    String description,
    Double price,
    Long quantity,
    String imageUrl,
    Boolean isActive
){}
