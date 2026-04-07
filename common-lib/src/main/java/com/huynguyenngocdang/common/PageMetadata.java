package com.huynguyenngocdang.common;

import org.springframework.data.domain.Page;

public record PageMetadata(
        Integer pageNumber,
        Integer pageSize,
        Integer numberOfElements,
        Long totalElements,
        Integer totalPages,
        Boolean isLast) {
    public static PageMetadata from(Page<?> page) {
        return new PageMetadata(
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast());
    }
}
