package com.hostmonolith.restapi.controller;

import com.hostmonolith.restapi.domain.ProjectEntity;
import com.hostmonolith.restapi.domain.ProjectMemberEntity;
import com.hostmonolith.restapi.domain.ProjectMetadataEntity;
import com.hostmonolith.restapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectEntity> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable UUID id) {
        return projectService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectEntity createProject(@RequestBody ProjectEntity project) {
        return projectService.create(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable UUID id, @RequestBody ProjectEntity updatedProject) {
        try {
            return ResponseEntity.ok(projectService.update(id, updatedProject));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        if (projectService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/metadata")
    public ResponseEntity<List<ProjectMetadataEntity>> getProjectMetadata(@PathVariable UUID id) {
        return projectService.findById(id)
            .map(project -> ResponseEntity.ok(projectService.getMetadata(id)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/metadata")
    public ResponseEntity<ProjectMetadataEntity> addProjectMetadata(@PathVariable UUID id, @RequestParam String key, @RequestParam String value) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addMetadata(id, key, value));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<ProjectMemberEntity>> getProjectMembers(@PathVariable UUID id) {
        return projectService.findById(id)
            .map(project -> ResponseEntity.ok(projectService.getMembers(id)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<ProjectMemberEntity> addProjectMember(@PathVariable UUID id, @RequestParam UUID userId, @RequestParam String role) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addMember(id, userId, role));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
