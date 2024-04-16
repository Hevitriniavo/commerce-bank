package com.tantely.commerce.dto.requests;

public record ProductRequest (

     String name,

     String description,

     Double price,

     Long quantity,

     String imageUrl,

     Boolean isActive
){}
