package com.project.knit.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class AdminDocument extends TimeEntity{
    @Column(name = "admin_documet_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "document_title", updatable = false)
    private String documentTitle;

    @Column(name = "document_sub_title")
    private String documentSubTitle;

    private String status;
}
