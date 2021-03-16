package com.project.knit.controller;

import com.project.knit.dto.req.DocumentCreateReqDto;
import com.project.knit.dto.req.DocumentUpdateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.DocumentResDto;
import com.project.knit.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Slf4j
@Validated
@RequestMapping("/v1/document")
@RequiredArgsConstructor
@RestController
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResDto> getDocumentInfoById(@PathVariable String documentId) {
        return new ResponseEntity<>(documentService.getDocumentInfoById(Long.valueOf(documentId)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CommonResponse> createDocument(@RequestBody DocumentCreateReqDto documentCreateReqDto) {
        return new ResponseEntity<>(documentService.createDocument(documentCreateReqDto), HttpStatus.OK);
    }

    @PatchMapping("/update/{documentId}")
    public ResponseEntity<CommonResponse> updateDocumentContent(@PathVariable @Valid @NotBlank(message = "Document Id should not be null or empty.") String documentId, @RequestBody DocumentUpdateReqDto documentUpdateReqDto) {
        return new ResponseEntity<>(documentService.updateDocumentContent(Long.valueOf(documentId), documentUpdateReqDto), HttpStatus.OK);
    }

    @DeleteMapping("{documentId}")
    public ResponseEntity<CommonResponse> deleteDocumentById(@PathVariable @Valid @NotBlank(message = "Document Id should not be null or empty.") String documentId) {
        return new ResponseEntity<>(documentService.deleteDocumentById(Long.valueOf(documentId)), HttpStatus.OK);
    }
}
