package com.project.knit.service;

import com.project.knit.domain.entity.Document;
import com.project.knit.domain.repository.DocumentRepository;
import com.project.knit.dto.req.DocumentCreateReqDto;
import com.project.knit.dto.req.DocumentUpdateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.DocumentResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentResDto getDocumentInfoById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        return DocumentResDto.builder().title(document.getTitle()).build();
    }

    @Transactional
    public CommonResponse createDocument(DocumentCreateReqDto documentCreateReqDto) {
        Document document = Document.builder()
                .title(documentCreateReqDto.getTitle())
                .html(documentCreateReqDto.getHtml())
                .build();

        documentRepository.save(document);

        return CommonResponse.builder().message("Document Successfully Created.").build();
    }

    @Transactional
    public CommonResponse updateDocumentContent(Long documentId, DocumentUpdateReqDto documentUpdateReqDto) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new NullPointerException("No data available."));

        document.update(document.getId(), document.getTitle(), documentUpdateReqDto.getHtml());

        return CommonResponse.builder().message("Document Successfully Updated.").build();
    }

    @Transactional
    public CommonResponse deleteDocumentById(Long documentId) {
        // toto any other validation?
        documentRepository.deleteById(documentId);

        return CommonResponse.builder().message("Document Deleted.").build();
    }
}
