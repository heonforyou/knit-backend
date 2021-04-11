package com.project.knit.controller;

import com.project.knit.dto.req.ThreadCreateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.ThreadListResDto;
import com.project.knit.dto.res.ThreadResDto;
import com.project.knit.dto.res.S3ImageResDto;
import com.project.knit.service.ThreadService;
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
public class ThreadController {

    private final ThreadService threadService;
    private final S3Service s3Service;

    @GetMapping("/thread/{threadId}")
    public ResponseEntity<ThreadResDto> getThreadInfoById(@PathVariable Long threadId) {
        return new ResponseEntity<>(threadService.getThreadInfoById(threadId), HttpStatus.OK);
    }

    @PostMapping("/v1/threads/register")
    public ResponseEntity<CommonResponse> registerThread(@RequestBody ThreadCreateReqDto threadCreateReqDto) {
        return new ResponseEntity<>(threadService.registerThread(threadCreateReqDto), HttpStatus.OK);
    }

    @PostMapping("/v1/threads/tag/{tagId}")
    public ResponseEntity<ThreadListResDto> getThreadListByTagId(@PathVariable Long tagId) {
        return new ResponseEntity<>(threadService.getThreadListByTagId(tagId), HttpStatus.OK);
    }

    @GetMapping("/v1/threads/tag/validation")
    public ResponseEntity<CommonResponse> checkTagName(@RequestParam String tagName) {
        return new ResponseEntity<>(threadService.checkTagName(tagName), HttpStatus.OK);
    }

    @PostMapping("/upload/thumbnail")
    public ResponseEntity<S3ImageResDto> uploadThreadThumbnail(MultipartFile multipartFile, @RequestPart String filename) {
        return new ResponseEntity<>(s3Service.uploadThumbnail(multipartFile, filename), HttpStatus.OK);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<S3ImageResDto> uploadThreadFile(MultipartFile multipartFile, @RequestPart String filename) {
        return new ResponseEntity<>(s3Service.uploadThreadFile(multipartFile, filename), HttpStatus.OK);
    }
}
