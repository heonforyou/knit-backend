package com.project.knit.domain.repository;

import com.project.knit.domain.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByThreadId(Long threadId);
}
