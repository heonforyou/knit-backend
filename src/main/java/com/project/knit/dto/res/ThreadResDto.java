package com.project.knit.dto.res;

import com.project.knit.domain.entity.Category;
import com.project.knit.domain.entity.Content;
import com.project.knit.domain.entity.Reference;
import com.project.knit.domain.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ThreadResDto {
    private Long threadId;
    private String threadTitle;
    private String threadSubTitle;
    private String threadThumbnail;
    private List<Content> contentList;
    private List<Tag> tagList;
    private List<Category> categoryList;
    private List<Reference> referenceList;

}
