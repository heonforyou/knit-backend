package com.project.knit.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class DocumentResDto {
    private Long documentId;
    private String title;
    private String html;
}
