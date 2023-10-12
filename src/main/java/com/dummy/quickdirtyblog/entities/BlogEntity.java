package com.dummy.quickdirtyblog.entities;

import static com.dummy.quickdirtyblog.utils.Utils.now;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(
    name = "blog",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "unique_blog_id_author_id",
          columnNames = {"blog_id", "author_id"})
    })
public class BlogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "blog_id", updatable = false, nullable = false, length = 512)
  private Long id;

  private String title;

  @Column(columnDefinition = "text")
  private String content;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  private LocalDateTime creationDate;

  private boolean draft;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "author_id", updatable = false, nullable = false)
  private AuthorEntity author;

  @PrePersist
  void addCreationDate() {
    this.creationDate = now();
  }
}
