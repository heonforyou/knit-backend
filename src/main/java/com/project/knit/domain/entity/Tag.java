package com.project.knit.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag")
@Getter
@Entity
public class Tag extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name", length = 45)
    private String tagName;

    @Column(name = "is_required", columnDefinition = "CHAR(1) NOT NULL COMMENT 'Y:직군태그, N:주제태그'")
    private String isRequired;
}
