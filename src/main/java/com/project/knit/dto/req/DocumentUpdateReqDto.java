package com.project.knit.dto.req;

import com.project.knit.domain.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentUpdateReqDto {
    private String subTitle;
    private String documentFileName;
    private List<Content> contentList;
}
