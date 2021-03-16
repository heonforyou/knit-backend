package com.project.knit.domain.repository;

import com.project.knit.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Document d set d.html = :html")
    void updateDocumentHtml(@Param("html") String html);
}
