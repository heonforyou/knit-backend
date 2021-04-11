package com.project.knit.service;

import com.project.knit.domain.entity.*;
import com.project.knit.domain.entity.Thread;
import com.project.knit.domain.repository.*;
import com.project.knit.dto.req.ThreadCreateReqDto;
import com.project.knit.dto.res.CommonResponse;
import com.project.knit.dto.res.ThreadListResDto;
import com.project.knit.dto.res.ThreadResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final ReferenceRepository referenceRepository;

    private final S3Service s3Service;
    private final AdminService adminService;

    public CommonResponse checkTagName(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);
        if(tag != null) {
            return CommonResponse.builder().message("Already Exists.").build();
        }
        return CommonResponse.builder().message("Avalilable Tag Name.").build();
    }

    public ThreadResDto getThreadInfoById(Long id) {
        Thread thread = threadRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        ThreadResDto resDto = new ThreadResDto();
        resDto.setCategoryList(thread.getCategoryList());
        resDto.setContentList(thread.getContentList());
        resDto.setReferenceList(thread.getReferenceList());
        resDto.setTagList(thread.getTagList());
        resDto.setThreadId(thread.getId());
        resDto.setThreadTitle(thread.getThreadTitle());
        resDto.setThreadSubTitle(thread.getThreadSubTitle());
        resDto.setThreadThumbnail(thread.getThreadThumbnail());

        return resDto;
    }

    @Transactional
    public CommonResponse registerThread(ThreadCreateReqDto threadCreateReqDto) {
        Thread thread = Thread.builder()
                .threadTitle(threadCreateReqDto.getTitle())
                .threadSubTitle(threadCreateReqDto.getSubTitle())
                .threadThumbnail(threadCreateReqDto.getThumbnail())
                .threadSummary(threadCreateReqDto.getSummary())
                .contentList(threadCreateReqDto.getContentList())
                .referenceList(threadCreateReqDto.getReferenceList())
                .tagList(threadCreateReqDto.getTagList())
                .categoryList(threadCreateReqDto.getCategoryList())
                .status("대기")
                .build();

        threadRepository.save(thread);

        for(Content c : threadCreateReqDto.getContentList()) {
            contentRepository.save(c);
        }
        for(Tag t : threadCreateReqDto.getTagList()) {
            tagRepository.save(t);
        }
        for(Category c : threadCreateReqDto.getCategoryList()) {
            categoryRepository.save(c);
        }
        for(Reference r : threadCreateReqDto.getReferenceList()) {
            referenceRepository.save(r);
        }

        return CommonResponse.builder().message("Thread on the waiting list.").build();
    }

    public ThreadListResDto getThreadListByTagId(Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        List<ThreadResDto> resDtoList = new ArrayList<>();

        List<Thread> threadList = threadRepository.findAllByTagListIn(tagList);
        for(Thread d : threadList) {
            ThreadResDto res  = new ThreadResDto();
            res.setThreadId(d.getId());
            res.setThreadTitle(d.getThreadTitle());
            res.setThreadSubTitle(d.getThreadSubTitle());
            res.setThreadThumbnail(d.getThreadThumbnail());
            res.setTagList(d.getTagList());
            res.setReferenceList(d.getReferenceList());
            res.setContentList(d.getContentList());
            res.setCategoryList(d.getCategoryList());

            resDtoList.add(res);
        }
        ThreadListResDto res = new ThreadListResDto();
        res.setCount(resDtoList.size());
        res.setThreadList(resDtoList);

        return res;
    }
}
