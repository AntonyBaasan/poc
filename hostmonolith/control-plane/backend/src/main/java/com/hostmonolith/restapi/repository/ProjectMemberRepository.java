package com.hostmonolith.restapi.repository;

import com.hostmonolith.restapi.domain.ProjectEntity;
import com.hostmonolith.restapi.domain.ProjectMemberEntity;
import com.hostmonolith.restapi.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, UUID> {

    List<ProjectMemberEntity> findByProject(ProjectEntity project);

    Optional<ProjectMemberEntity> findByProjectAndUser(ProjectEntity project, UserEntity user);
}
