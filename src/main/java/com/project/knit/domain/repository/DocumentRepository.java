package com.project.knit.domain.repository;

import com.project.knit.domain.entity.Document;
import com.project.knit.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByTagList(List<Tag> tagList);

}
