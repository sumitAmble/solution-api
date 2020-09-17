package com.solution.solutionapi.DTO;

import java.util.Date;
import java.util.List;

import com.solution.solutionapi.model.Comment;

public class StoriesDTO {

	private Long id;

	private Date time;
	private String text;
	private String url;
	private Integer score;
	private String title;
	private String user;

	public StoriesDTO(Long id,Date time, String text, String url, Integer score, String title,String user) {
		super();
		this.id = id;
		this.time = time;
		this.text = text;
		this.url = url;
		this.score = score;
		this.title = title;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
