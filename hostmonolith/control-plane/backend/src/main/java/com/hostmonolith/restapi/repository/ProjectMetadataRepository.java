package com.hostmonolith.restapi.repository;

import com.hostmonolith.restapi.domain.ProjectEntity;
import com.hostmonolith.restapi.domain.ProjectMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectMetadataRepository extends JpaRepository<ProjectMetadataEntity, UUID> {

    List<ProjectMetadataEntity> findByProject(ProjectEntity project);
}
