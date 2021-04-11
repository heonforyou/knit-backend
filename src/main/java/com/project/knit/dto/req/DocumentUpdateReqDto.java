package com.project.knit.dto.req;

import com.project.knit.domain.entity.Content;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DocumentUpdateReqDto {
    private String subTitle;
    private String documentFileName;
    private List<Content> contentList;
}
