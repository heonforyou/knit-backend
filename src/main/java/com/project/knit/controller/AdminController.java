package com.project.knit.controller;

import com.project.knit.dto.res.CommonResponse;
import com.project.knit.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/accept")
    public ResponseEntity<CommonResponse> acceptThread(@PathVariable Long threadId) {
        return new ResponseEntity<>(adminService.acceptThread(threadId), HttpStatus.OK);
    }

    @PostMapping("/decline")
    public ResponseEntity<CommonResponse> declineThread(@PathVariable Long threadId) {
        return new ResponseEntity<>(adminService.declineThread(threadId), HttpStatus.OK);
    }
}
