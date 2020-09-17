package com.solution.solutionapi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solution.solutionapi.DTO.CommentDTO;
import com.solution.solutionapi.DTO.StoriesDTO;
import com.solution.solutionapi.model.Comment;
import com.solution.solutionapi.model.Stories;
import com.solution.solutionapi.repo.StoriesRepo;

@Service
public class StoriesServiceImpl {

	@Autowired
	private StoriesRepo storiesRepo;

	public List<StoriesDTO> getBestStories() {
		return getStories(1);
	}

	public List<StoriesDTO> getPastStories() {
		return getStories(2);
	}
	
	public List<StoriesDTO> getStories(int latest){
		return storiesRepo.find(latest)
				.stream()
				.map(st->StoryModelToDTO(st))
				.collect(Collectors.toList());
	}
	
	
	public List<CommentDTO> getTopComments(Long id) throws Exception{
		Optional<Stories> s = storiesRepo.findById(id);
		if(s.isPresent()) {
			List<Comment> list =s.get().getComments();
			list = list.stream().filter(sd->sd.getKids()!=null).collect(Collectors.toList());
			list.sort((a1,a)->a.getKids().length-a1.getKids().length);
			Collections.reverse(list);
			return list.stream().map(cm->CommentModelToDTO(cm)).collect(Collectors.toList());
		}else {
			throw new Exception("No story with id "+id);
		}
	}
	
	private StoriesDTO StoryModelToDTO(Stories story){
		return new StoriesDTO(story.getId(), story.getTime(),story.getText(), story.getUrl(),story.getScore(),story.getTitle(),story.getBy());
	}
	
	private CommentDTO CommentModelToDTO(Comment comment){
		return new CommentDTO(comment.getId(), comment.getTime(), comment.getText(),comment.getUrl(),comment.getBy(),comment.getUserCreatedDate());
		
	}
}
