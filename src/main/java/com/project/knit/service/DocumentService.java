package com.project.knit.service;

import com.project.knit.domain.entity.Document;
import com.project.knit.domain.entity.Tag;
import com.project.knit.domain.repository.DocumentRepository;
import com.project.knit.domain.repository.TagRepository;
import com.project.knit.dto.req.DocumentCreateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.DocumentResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final TagRepository tagRepository;
    private final S3Service s3Service;
    private final AdminService adminService;

    public CommonResponse checkTagName(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);
        if(tag != null) {
            return CommonResponse.builder().message("Already Exists.").build();
        }
        return CommonResponse.builder().message("Avalilable Tag Name.").build();
    }

    public DocumentResDto getDocumentInfoById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        DocumentResDto resDto = new DocumentResDto();
        resDto.setCategoryList(document.getCategoryList());
        resDto.setContentList(document.getContentList());
        resDto.setReferenceList(document.getReferenceList());
        resDto.setTagList(document.getTagList());
        resDto.setDocumentId(document.getId());
        resDto.setDocumentTitle(document.getDocumentTitle());
        resDto.setDocumentSubTitle(document.getDocumentSubTitle());
        resDto.setDocumentThumbnail(document.getDocumentThumbnail());

        return DocumentResDto.builder().documentTitle(document.getDocumentTitle()).build();
    }

    @Transactional
    public CommonResponse registerDocument(DocumentCreateReqDto documentCreateReqDto) {
        Document document = Document.builder()
                .documentTitle(documentCreateReqDto.getTitle())
                .documentSubTitle(documentCreateReqDto.getSubTitle())
                .documentThumbnail(documentCreateReqDto.getThumbnail())
                .referenceList(documentCreateReqDto.getReferenceList())
                .tagList(documentCreateReqDto.getTagList())
                .categoryList(documentCreateReqDto.getCategoryList())
                .status("대기")
                .build();

        documentRepository.save(document);

        return CommonResponse.builder().message("Document on the waiting list.").build();
    }

    public List<DocumentResDto> getDocumentListByTagId(Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        List<DocumentResDto> resDtoList = new ArrayList<>();

        List<Document> documentList = documentRepository.findAllByTagList(tagList);
        for(Document d : documentList) {
            DocumentResDto res  = new DocumentResDto();
            res.setDocumentId(d.getId());
            res.setDocumentTitle(d.getDocumentTitle());
            res.setDocumentSubTitle(d.getDocumentSubTitle());
            res.setDocumentThumbnail(d.getDocumentThumbnail());
            res.setTagList(d.getTagList());
            res.setReferenceList(d.getReferenceList());
            res.setContentList(d.getContentList());
            res.setCategoryList(d.getCategoryList());

            resDtoList.add(res);
        }

        return resDtoList;
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
