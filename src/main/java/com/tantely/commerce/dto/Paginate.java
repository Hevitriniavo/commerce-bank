package com.tantely.commerce.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Paginate <T> {

    private List<T> content;

    private long totalElements;

    private int totalPages;

    private int numberOfElements;

    private boolean hasNextPage;

    private boolean hasPreventPage;
}
