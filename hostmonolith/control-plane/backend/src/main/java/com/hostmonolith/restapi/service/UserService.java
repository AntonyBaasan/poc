package com.hostmonolith.restapi.service;

import com.hostmonolith.restapi.domain.UserEntity;
import com.hostmonolith.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity update(UUID id, UserEntity updatedUser) {
        UserEntity existing = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        return userRepository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
