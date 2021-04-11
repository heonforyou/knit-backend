package com.project.knit.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.knit.utils.enums.DocumentType;
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

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String value;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @Builder
    public Content(DocumentType documentType, String value, Document document) {
        this.documentType = documentType;
        this.value = value;
        this.document = document;
    }
}
