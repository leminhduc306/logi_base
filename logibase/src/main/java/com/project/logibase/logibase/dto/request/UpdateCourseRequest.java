package com.project.logibase.logibase.dto.request;

import com.project.logibase.logibase.constant.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data

public class UpdateCourseRequest {


    private String title;

    private String description;

    private String thumbnail;

    private BigDecimal price = BigDecimal.ZERO;

}
