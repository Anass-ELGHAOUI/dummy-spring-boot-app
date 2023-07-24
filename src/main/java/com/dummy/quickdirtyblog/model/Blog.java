package com.dummy.quickdirtyblog.model;

import static com.dummy.quickdirtyblog.utils.Utils.now;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Blog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String title;

  @Column(columnDefinition = "text")
  private String content;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  private LocalDateTime creationDate;

  private boolean draft;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Author author;

  @PrePersist
  void addCreationDate() {
    this.creationDate = now();
  }
}
