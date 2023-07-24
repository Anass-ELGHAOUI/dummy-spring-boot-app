package com.dummy.quickdirtyblog.repositories;

import com.dummy.quickdirtyblog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, String> {

  Page<Blog> findByTitleContainingIgnoreCase(String title, PageRequest pageRequest);
  Page<Blog> findAllByDraftFalse(PageRequest pageRequest);
}
