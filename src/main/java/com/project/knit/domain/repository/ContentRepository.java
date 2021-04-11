package com.project.knit.domain.repository;

import com.project.knit.domain.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
