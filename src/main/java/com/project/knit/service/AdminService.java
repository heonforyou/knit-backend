package com.project.knit.service;

import com.project.knit.domain.entity.Thread;
import com.project.knit.domain.repository.ThreadRepository;
import com.project.knit.dto.res.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminService {

    private final ThreadRepository threadRepository;

    public CommonResponse acceptThread(Long threadId) {
        Thread thread = threadRepository.getOne(threadId);
        thread.changeStatus("승인");

        threadRepository.save(thread);
        // ThreadCategory save
        // ThreadTag save
        // ThreadReference save

        return CommonResponse.builder().message("Thread Successfully Created.").build();
    }

    public CommonResponse declineThread(Long threadId) {
        Thread thread = threadRepository.getOne(threadId);
        thread.changeStatus("반려");

        threadRepository.save(thread);

        return CommonResponse.builder().message("Thread Declined.").build();
    }
}
