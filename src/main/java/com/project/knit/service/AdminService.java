package com.project.knit.service;

import com.project.knit.domain.entity.Document;
import com.project.knit.domain.repository.DocumentRepository;
import com.project.knit.dto.res.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminService {

    private final DocumentRepository documentRepository;

    public CommonResponse acceptDocument(Long documentId) {
        Document document = documentRepository.getOne(documentId);
        document.changeStatus("승인");

        documentRepository.save(document);

        return CommonResponse.builder().message("Document Successfully Created.").build();
    }

    public CommonResponse declineDocument(Long documentId) {
        Document document = documentRepository.getOne(documentId);
        document.changeStatus("반려");

        documentRepository.save(document);

        return CommonResponse.builder().message("Document Declined.").build();
    }
}
