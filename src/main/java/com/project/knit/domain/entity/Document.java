package com.project.knit.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Document extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_title", updatable = false)
    private String title;

    @Column(name = "document_html")
    private String html;

    @Builder
    public Document(String title, String html) {
        this.title = title;
        this.html = html;
    }

    public void update(Long id, String title, String html) {
        this.id = id;
        this.title = title;
        this.html = html;
    }
}
