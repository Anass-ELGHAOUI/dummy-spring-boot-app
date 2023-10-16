package com.dummy.quickdirtyblog.repositories;

import com.dummy.quickdirtyblog.entities.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

  Page<BlogEntity> findByTitleContainingIgnoreCaseAndDraftEquals(
      String title, boolean draft, PageRequest pageRequest);

  Page<BlogEntity> findAllByDraftEquals(boolean draft, PageRequest pageRequest);
}
