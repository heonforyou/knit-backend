package com.project.knit.domain.entity;

import com.project.knit.domain.entity.TimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Document extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_title")
    private String title;
}
