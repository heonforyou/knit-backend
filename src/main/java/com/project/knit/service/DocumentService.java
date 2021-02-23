package com.project.knit.service;

import com.project.knit.domain.entity.Document;
import com.project.knit.domain.repository.DocumentRepository;
import com.project.knit.dto.res.DocumentResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentResDto getDocumentInfoById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        return DocumentResDto.builder().title(document.getTitle()).build();
    }
}
