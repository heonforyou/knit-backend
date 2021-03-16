package com.project.knit.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResDto {
    private Long documentId;
    private String title;
    private String html;
}
