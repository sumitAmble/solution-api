package com.solution.solutionapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.persistence.*;


@Entity
public class Comment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="value_type")
	private String type;
	
	@Temporal(TemporalType.TIME)
	private Date time;
	@Lob
	@Column(length = 7000)
	private String text;
	private String url;
	private Integer score;
	private String title;
	@OrderColumn(name="childs")
	@ElementCollection
	private int[] kids;
	@Column(name="by_user")
	private String by;

	@JsonBackReference("product-category")
	@ManyToOne(cascade = CascadeType.ALL)
	private Stories stories;

	@Column(name = "userCreated")
	@Temporal(TemporalType.TIME)
	private Date userCreatedDate;

	public Comment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Stories getStories() {
		return stories;
	}

	public void setStories(Stories stories) {
		this.stories = stories;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		byte[] b = text.getBytes();
		this.text = new String(b, StandardCharsets.UTF_8);
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

	public int[] getKids() {
		return kids;
	}

	public void setKids(int[] kids) {
		this.kids = kids;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public Date getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(Date userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}
}
