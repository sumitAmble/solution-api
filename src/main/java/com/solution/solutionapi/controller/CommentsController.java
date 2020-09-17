package com.solution.solutionapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solution.solutionapi.DTO.CommentDTO;
import com.solution.solutionapi.service.StoriesServiceImpl;

@RestController
@RequestMapping(value = "/solution")
public class CommentsController {
	
	@Autowired
	private StoriesServiceImpl storiesService;
	
	@GetMapping("/{id}/comments")
	public ResponseEntity<List<CommentDTO>> getModel(@PathVariable("id") Long id) throws Exception{
		return ResponseEntity.ok(storiesService.getTopComments(id));
	}
	
}
