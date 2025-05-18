package com.project.logibase.logibase.dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T> {
    List<T> content;
    PageCustom<T> pageCustom;

    public PageResponse(Page<T> page) {
        content = page.getContent();
        pageCustom = new PageCustom<>(page);
    }
}
