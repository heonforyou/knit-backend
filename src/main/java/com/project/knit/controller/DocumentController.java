package com.project.knit.controller;

import com.project.knit.dto.req.DocumentCreateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.DocumentResDto;
import com.project.knit.dto.res.S3ImageResDto;
import com.project.knit.service.DocumentService;
import com.project.knit.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class DocumentController {

    private final DocumentService documentService;
    private final S3Service s3Service;

    @GetMapping("/thread/{threadId}")
    public ResponseEntity<DocumentResDto> getDocumentInfoById(@PathVariable Long threadId) {
        return new ResponseEntity<>(documentService.getDocumentInfoById(threadId), HttpStatus.OK);
    }

    @PostMapping("/v1/document/register")
    public ResponseEntity<CommonResponse> registerDocument(@RequestBody DocumentCreateReqDto documentCreateReqDto) {
        return new ResponseEntity<>(documentService.registerDocument(documentCreateReqDto), HttpStatus.OK);
    }

    @PostMapping("/v1/threads/tag/{tagId}")
    public ResponseEntity<List<DocumentResDto>> getDocumentListByTagId(@PathVariable Long tagId) {
        return new ResponseEntity<>(documentService.getDocumentListByTagId(tagId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse> checkTagName(@RequestParam String tagName) {
        return new ResponseEntity<>(documentService.checkTagName(tagName), HttpStatus.OK);
    }

//    @PatchMapping("/add/content/{documentId}")
//    public ResponseEntity<CommonResponse> addDocumentContent(@PathVariable @Valid @NotBlank(message = "Document Id should not be null or empty.") String documentId, MultipartFile multipartFile, @RequestBody DocumentUpdateReqDto documentUpdateReqDto) {
//        return new ResponseEntity<>(documentService.addDocumentContent(Long.valueOf(documentId), multipartFile, documentUpdateReqDto), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{documentId}")
//    public ResponseEntity<CommonResponse> deleteDocumentById(@PathVariable @Valid @NotBlank(message = "Document Id should not be null or empty.") String documentId) {
//        return new ResponseEntity<>(documentService.deleteDocumentById(Long.valueOf(documentId)), HttpStatus.OK);
//    }

    @PostMapping("/upload/thumbnail")
    public ResponseEntity<S3ImageResDto> uploadDocumentThumbnail(MultipartFile multipartFile, @RequestPart String filename) {
        return new ResponseEntity<>(s3Service.uploadThumbnail(multipartFile, filename), HttpStatus.OK);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<S3ImageResDto> uploadDocumentFile(MultipartFile multipartFile, @RequestPart String filename) {
        return new ResponseEntity<>(s3Service.uploadDocumentFile(multipartFile, filename), HttpStatus.OK);
    }
}
