package com.project.knit.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "thread_tag")
@Getter
@Entity
public class ThreadTag extends TimeEntity {
    @Column(name = "thread_tag_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thread_id")
    private Long threadId;

    @Column(name = "tag_id")
    private Long tagId;
}
