package com.dummy.quickdirtyblog.repositories;

import com.dummy.quickdirtyblog.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {}
