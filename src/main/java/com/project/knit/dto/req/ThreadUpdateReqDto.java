package com.project.knit.dto.req;

import com.project.knit.domain.entity.Content;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ThreadUpdateReqDto {
    private String subTitle;
    private String threadFileName;
    private List<Content> contentList;
}
