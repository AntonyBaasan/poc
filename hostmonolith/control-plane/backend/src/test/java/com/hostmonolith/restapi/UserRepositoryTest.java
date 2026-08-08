package com.hostmonolith.restapi;

import com.hostmonolith.restapi.domain.UserEntity;
import com.hostmonolith.restapi.repository.UserRepository;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    @Test
    void shouldExposeExpectedJpaDomainContract() throws NoSuchMethodException {
        assertThat(UserEntity.class.getAnnotation(Entity.class)).isNotNull();
        assertThat(UserRepository.class.isInterface()).isTrue();
        assertThat(UserRepository.class.getMethod("findByUsername", String.class)).isNotNull();
        assertThat(UserRepository.class.getMethod("findByEmail", String.class)).isNotNull();
    }
}
