package com.project.knit.controller;

import com.project.knit.dto.res.DocumentResDto;
import com.project.knit.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/document")
@RestController
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResDto> getDocumentInfoById(@PathVariable String documentId) {
        return new ResponseEntity<>(documentService.getDocumentInfoById(Long.valueOf(documentId)), HttpStatus.OK);
    }
}
