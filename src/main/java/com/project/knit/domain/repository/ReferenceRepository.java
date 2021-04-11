package com.project.knit.domain.repository;

import com.project.knit.domain.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
