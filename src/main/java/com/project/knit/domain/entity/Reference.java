package com.project.knit.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reference")
@Getter
@Entity
public class Reference extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_link", columnDefinition = "VARCHAR(500)", nullable = false)
    private String referenceLink;

    @Column(name = "reference_description", columnDefinition = "VARCHAR(500) COMMENT '참조링크에 대한 설명'")
    private String referenceDescription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @Builder
    public Reference(String referenceLink, String referenceDescription, Document document) {
        this.referenceLink = referenceLink;
        this.referenceDescription = referenceDescription;
        this.document = document;
    }
}
