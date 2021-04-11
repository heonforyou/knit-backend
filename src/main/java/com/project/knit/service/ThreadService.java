package com.project.knit.service;

import com.project.knit.domain.entity.*;
import com.project.knit.domain.entity.Thread;
import com.project.knit.domain.repository.*;
import com.project.knit.dto.req.ThreadCreateReqDto;
import com.project.knit.dto.res.*;
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
        CommonResponse response = new CommonResponse();
        Tag tag = tagRepository.findByTagName(tagName);
        if(tag != null) {
            response.setMessage("Already Exists.");
        } else {
            response.setMessage("Available Tag Name.");
        }

        return response;
    }

    public ThreadResDto getThreadInfoById(Long id) {
        Thread thread = threadRepository.findById(id)
                .orElseThrow(NullPointerException::new);
        Long threadId = thread.getId();

        List<Content> contentList = contentRepository.findAllByThreadId(threadId);
        List<Category> categoryList = categoryRepository.findAllByThreadId(threadId);
        List<Tag> tagList = tagRepository.findAllByThreadId(threadId);
        List<Reference> referenceList = referenceRepository.findAllByThreadId(threadId);

        List<ContentResDto> contentResList = new ArrayList<>();
        List<CategoryResDto> categoryResList = new ArrayList<>();
        List<TagResDto> tagResList = new ArrayList<>();
        List<ReferenceResDto> referenceResList = new ArrayList<>();

        for(Content c : contentList) {
            ContentResDto res = new ContentResDto();
            res.setContentId(c.getId());
            res.setType(c.getThreadType().name());
            res.setValue(c.getValue());
            res.setSummary(c.getSummary() == null ? null : c.getSummary());

            contentResList.add(res);
        }
        for(Category c :categoryList) {
            CategoryResDto res = new CategoryResDto();
            res.setCategoryId(c.getId());
            res.setCategory(c.getCategory());

            categoryResList.add(res);
        }
        for(Tag t : tagList) {
            TagResDto res = new TagResDto();
            res.setTagId(t.getId());
            res.setTag(t.getTagName());

            tagResList.add(res);
        }
        for(Reference r : referenceList) {
            ReferenceResDto res = new ReferenceResDto();
            res.setReferenceId(r.getId());
            res.setReferenceLink(r.getReferenceLink());
            res.setReferenceDescription(r.getReferenceDescription());

            referenceResList.add(res);
        }
        
        ThreadResDto resDto = new ThreadResDto();
        resDto.setCategoryList(categoryResList);
        resDto.setContentList(contentResList);
        resDto.setReferenceList(referenceResList);
        resDto.setTagList(tagResList);
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

        Thread createdThread = threadRepository.save(thread);


        for(Content c : threadCreateReqDto.getContentList()) {
            contentRepository.save(c);
            c.addThread(createdThread);
        }
        for(Tag t : threadCreateReqDto.getTagList()) {
            tagRepository.save(t);
            t.addThread(createdThread);
        }
        for(Category c : threadCreateReqDto.getCategoryList()) {
            categoryRepository.save(c);
            c.addThread(createdThread);
        }
        for(Reference r : threadCreateReqDto.getReferenceList()) {
            referenceRepository.save(r);
            r.addThread(createdThread);
        }

        CommonResponse response = new CommonResponse();
        response.setMessage("Thread on the waiting list.");

        return response;
    }

    public ThreadListResDto getThreadListByTagId(Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        List<ThreadAdminResDto> resDtoList = new ArrayList<>();

        List<Thread> threadList = threadRepository.findAllByTagListIn(tagList);
        for(Thread d : threadList) {
            ThreadAdminResDto res  = new ThreadAdminResDto();
            
            res.setNickname("테스트닉네임");
            res.setThreadId(d.getId());
            res.setThreadTitle(d.getThreadTitle());
            res.setThreadSubTitle(d.getThreadSubTitle());
            res.setThreadThumbnail(d.getThreadThumbnail());
            res.setTagList(d.getTagList());
            res.setReferenceList(d.getReferenceList());
//            res.setContentList(d.getContentList());
//            res.setCategoryList(d.getCategoryList());

            resDtoList.add(res);
        }
        ThreadListResDto res = new ThreadListResDto();
        res.setCount(resDtoList.size());
        res.setThreadList(resDtoList);

        return res;
    }
}
