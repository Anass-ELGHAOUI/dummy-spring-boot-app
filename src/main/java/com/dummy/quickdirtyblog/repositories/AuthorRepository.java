package com.dummy.quickdirtyblog.repositories;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findAuthorByNameContainsIgnoreCase(String name);
}
