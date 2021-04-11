package com.project.knit.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class Content extends TimeEntity {
    @Column(name = "content_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String value;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @Builder
    public Content(String type, String value, Document document) {
        this.type = type;
        this.value = value;
        this.document = document;
    }
}
