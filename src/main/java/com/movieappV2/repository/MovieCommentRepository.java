package com.movieappV2.repository;

import com.movieappV2.model.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {
    Optional<MovieComment> findByBody(String body);
}
