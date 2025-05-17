package com.project.logibase.logibase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class IntrospectResponse {
    boolean isValid;
}
