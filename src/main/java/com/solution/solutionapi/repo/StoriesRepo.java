package com.solution.solutionapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solution.solutionapi.model.Stories;

public interface StoriesRepo extends JpaRepository<Stories, Long> {
	
	@Query("SELECT s FROM Stories s where s.latest =?1")
	public List<Stories> find(int latest);

	@Query("SELECT s FROM Stories s WHERE s.latest IN (:list)")
	public List<Stories> getStories(List<Integer> list);
	
}
