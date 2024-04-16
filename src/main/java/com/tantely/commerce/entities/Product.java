package com.tantely.commerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_price", columnList = "price")
})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String description;

    private Long quantity;

    private String imageUrl;

    @Builder.Default
    private Boolean isActive = true;

    @ManyToOne
    private Category category;
}
