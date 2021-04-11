package com.project.knit.dto.req;

import com.project.knit.domain.entity.Category;
import com.project.knit.domain.entity.Reference;
import com.project.knit.domain.entity.Tag;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DocumentCreateReqDto {
    private String title;
    private String subTitle;
    private String thumbnail;
    private List<Tag> tagList;
    private List<Category> categoryList;
    private List<Reference> referenceList;
}
