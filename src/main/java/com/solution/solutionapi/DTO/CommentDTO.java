package com.solution.solutionapi.DTO;

import java.util.Date;

public class CommentDTO {

	private Long id;
	private Date time;
	private String text;
	private String url;
	private String user;
	private Date userCreated;

	public CommentDTO(Long id, Date time, String text, String url,
			String user, Date userCreated) {
		super();
		this.id = id;
		this.time = time;
		this.text = text;
		this.url = url;
		this.user = user;
		this.userCreated = userCreated;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public Date getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Date userCreated) {
		this.userCreated = userCreated;
	}
}
