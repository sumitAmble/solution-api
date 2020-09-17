package com.solution.solutionapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solution.solutionapi.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
