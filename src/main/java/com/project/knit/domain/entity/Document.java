package com.project.knit.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Document extends TimeEntity {

    @Column(name = "documet_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_title", updatable = false)
    private String documentTitle;

    @Column(name = "document_sub_title")
    private String documentSubTitle;

    @Column(name = "document_thumbnail")
    private String documentThumbnail;

    @JsonManagedReference
    @OneToMany(mappedBy = "document")
    private List<Content> contentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "document")
    private List<Tag> tagList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "document")
    private List<Category> categoryList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "document")
    private List<Reference> referenceList = new ArrayList<>();

    private String status;

    @Builder
    public Document(String documentTitle, String documentSubTitle, String documentThumbnail, List<Reference> referenceList, List<Tag> tagList, List<Category> categoryList, String status) {
        this.documentTitle = documentTitle;
        this.documentSubTitle = documentSubTitle;
        this.documentThumbnail = documentThumbnail;
        this.referenceList = referenceList;
        this.tagList = tagList;
        this.categoryList = categoryList;
        this.status = status;
    }

    public void changeStatus(String status) {
        this.status = status;
    }

    public void addContent(Content content) {
        this.contentList.add(content);
    }

    public void addTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public void addCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
