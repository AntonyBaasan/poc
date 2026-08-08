package com.hostmonolith.restapi.service;

import com.hostmonolith.restapi.domain.ProjectEntity;
import com.hostmonolith.restapi.domain.ProjectMemberEntity;
import com.hostmonolith.restapi.domain.ProjectMetadataEntity;
import com.hostmonolith.restapi.domain.UserEntity;
import com.hostmonolith.restapi.repository.ProjectMemberRepository;
import com.hostmonolith.restapi.repository.ProjectMetadataRepository;
import com.hostmonolith.restapi.repository.ProjectRepository;
import com.hostmonolith.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMetadataRepository projectMetadataRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;

    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    public Optional<ProjectEntity> findById(UUID id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public ProjectEntity create(ProjectEntity project) {
        return projectRepository.save(project);
    }

    @Transactional
    public ProjectEntity update(UUID id, ProjectEntity updatedProject) {
        ProjectEntity existing = projectRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Project not found: " + id));

        existing.setName(updatedProject.getName());
        existing.setDescription(updatedProject.getDescription());
        existing.setStatus(updatedProject.getStatus());
        existing.setVersion(updatedProject.getVersion());
        return projectRepository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectMetadataEntity> getMetadata(UUID projectId) {
        ProjectEntity project = projectRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));
        return projectMetadataRepository.findByProject(project);
    }

    @Transactional
    public ProjectMetadataEntity addMetadata(UUID projectId, String key, String value) {
        ProjectEntity project = projectRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));

        ProjectMetadataEntity metadata = new ProjectMetadataEntity();
        metadata.setProject(project);
        metadata.setKey(key);
        metadata.setValue(value);
        return projectMetadataRepository.save(metadata);
    }

    public List<ProjectMemberEntity> getMembers(UUID projectId) {
        ProjectEntity project = projectRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));
        return projectMemberRepository.findByProject(project);
    }

    @Transactional
    public ProjectMemberEntity addMember(UUID projectId, UUID userId, String role) {
        ProjectEntity project = projectRepository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        ProjectMemberEntity member = new ProjectMemberEntity();
        member.setProject(project);
        member.setUser(user);
        member.setRole(role);
        return projectMemberRepository.save(member);
    }
}
