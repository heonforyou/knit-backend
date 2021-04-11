package com.project.knit.service;

import com.project.knit.domain.entity.Content;
import com.project.knit.domain.entity.Document;
import com.project.knit.domain.repository.DocumentRepository;
import com.project.knit.dto.req.DocumentCreateReqDto;
import com.project.knit.dto.req.DocumentUpdateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.DocumentResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final S3Service s3Service;
    private final AdminService adminService;

    public DocumentResDto getDocumentInfoById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        return DocumentResDto.builder().title(document.getDocumentTitle()).build();
    }

    @Transactional
    public CommonResponse registerDocument(DocumentCreateReqDto documentCreateReqDto) {
        Document document = Document.builder()
                .documentTitle(documentCreateReqDto.getTitle())
                .documentSubTitle(documentCreateReqDto.getSubTitle())
                .documentThumbnail(documentCreateReqDto.getThumbnail())
                .status("대기")
                .build();

        documentRepository.save(document);

        return CommonResponse.builder().message("Document Successfully Registered.").build();
    }
//
//    @Transactional
//    public CommonResponse addDocumentContent(Long documentId, DocumentUpdateReqDto documentUpdateReqDto) {
//        Document document = documentRepository.findById(documentId)
//                .orElseThrow(() -> new NullPointerException("No data available."));
//
//
//        Content content = Content.builder()
//                .type()
//                .value()
//                .document(document)
//                .build();
//        document.addContent(content);
//
//        return CommonResponse.builder().message("Document Successfully Updated.").build();
//    }

    @Transactional
    public CommonResponse deleteDocumentById(Long documentId) {
        // todo any other validation?
        documentRepository.deleteById(documentId);

        return CommonResponse.builder().message("Document Deleted.").build();
    }
}
