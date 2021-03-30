package com.project.knit.domain.entity;

import lombok.AccessLevel;
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

    // document_id : mapping or

    @Column(name = "reference_link", columnDefinition = "VARCHAR(500)", nullable = false)
    private String referenceLink;

    @Column(name = "reference_link", columnDefinition = "VARCHAR(500) COMMENT '참조링크에 대한 설명'")
    private String description;
}
