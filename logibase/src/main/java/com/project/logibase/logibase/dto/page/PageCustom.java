package com.project.logibase.logibase.dto.page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageCustom<T> {

    int pageSize;
    int totalPages;
    int totalElements;
    int currentPage;

    public PageCustom(Page<T> page) {
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.totalElements = page.getNumberOfElements();
    }
}
