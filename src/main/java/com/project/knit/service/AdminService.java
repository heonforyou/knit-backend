package com.project.knit.service;

import com.project.knit.domain.entity.Thread;
import com.project.knit.domain.repository.ThreadRepository;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.ThreadAdminResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        CommonResponse response = new CommonResponse();
        response.setMessage("Thread Successfully Created.");

        return response;
    }

    public CommonResponse declineThread(Long threadId) {
        Thread thread = threadRepository.getOne(threadId);
        thread.changeStatus("반려");

        threadRepository.save(thread);

        CommonResponse response = new CommonResponse();
        response.setMessage("Thread Declined.");

        return response;
    }

    public List<ThreadAdminResDto> getAllThreadList() {
        List<Thread> threadList = threadRepository.findAll();
        List<ThreadAdminResDto> resDtoList = new ArrayList<>();

        for(Thread t : threadList) {
            ThreadAdminResDto res = new ThreadAdminResDto();
            res.setThreadId(t.getId());
            res.setThreadTitle(t.getThreadTitle());
            res.setThreadSubTitle(t.getThreadSubTitle());
            res.setThreadThumbnail(t.getThreadThumbnail());
            res.setContentList(t.getContentList());
            res.setCategoryList(t.getCategoryList());
            res.setTagList(t.getTagList());
            res.setReferenceList(t.getReferenceList());
            res.setStatus(t.getStatus());
            res.setNickname("닉네임테스트");

            resDtoList.add(res);
        }

        return resDtoList;
    }

    public List<ThreadAdminResDto> getThreadListByStatus(String status) {
        List<Thread> threadList = threadRepository.findAllByStatus(status);
        List<ThreadAdminResDto> resDtoList = new ArrayList<>();

        for(Thread t : threadList) {
            ThreadAdminResDto res = new ThreadAdminResDto();
            res.setThreadId(t.getId());
            res.setThreadTitle(t.getThreadTitle());
            res.setThreadSubTitle(t.getThreadSubTitle());
            res.setThreadThumbnail(t.getThreadThumbnail());
            res.setContentList(t.getContentList());
            res.setCategoryList(t.getCategoryList());
            res.setTagList(t.getTagList());
            res.setReferenceList(t.getReferenceList());
            res.setStatus(t.getStatus());
            res.setNickname("닉네임테스트");

            resDtoList.add(res);
        }

        return resDtoList;
    }
}
