package com.solution.solutionapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solution.solutionapi.DTO.StoriesDTO;
import com.solution.solutionapi.service.StoriesServiceImpl;

@RestController
@RequestMapping("/solution")
public class StoriesController {

	@Autowired
	private StoriesServiceImpl storiesService;
	
	@GetMapping("/best-stories")
	public ResponseEntity<List<StoriesDTO>> getLatestStories() {
		return ResponseEntity.ok(storiesService.getBestStories());
	}
	
	@GetMapping("/past-stories")
	public ResponseEntity<List<StoriesDTO>> getPastStories() {
		return ResponseEntity.ok(storiesService.getPastStories());
	}
}
