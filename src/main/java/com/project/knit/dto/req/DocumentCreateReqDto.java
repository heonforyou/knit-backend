package com.project.knit.dto.req;

import com.project.knit.domain.entity.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreateReqDto {
    private String title;
    private String subTitle;
    private String thumbnail;
    private List<Reference> referenceList;
}
