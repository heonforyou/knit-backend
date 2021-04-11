package com.project.knit.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;

    @Builder
    public Reference(String referenceLink, String referenceDescription, Thread thread) {
        this.referenceLink = referenceLink;
        this.referenceDescription = referenceDescription;
        this.thread = thread;
    }
}
