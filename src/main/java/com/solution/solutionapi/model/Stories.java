package com.solution.solutionapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
public class Stories implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="value_type")
	private String type;
	@Temporal(TemporalType.TIME)
	private Date time;
	@Lob
	@Column(length = 5000)
	private String text;
	private String url;
	private Integer score;
	private String title;
	private int latest;
	@Column(name="by_user")
	private String by;
	
	@Transient
	private int[] kids;

	@JsonManagedReference("product-category")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stories")
	private List<Comment> comments = new ArrayList<>();
	
	public Stories() {}
	
	public Stories(String by,Long id,int[]kids,int score,Date time,String title,String type,String url) {
		this.by = by;
		this.id = id;
		this.kids = kids;
		this.score = score;
		this.time = time;
		this.title = title;
		this.type = type;
		this.url = url;
	}

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int[] getKids() {
		return kids;
	}

	public void setKids(int[] kids) {
		this.kids = kids;
	}

	public int getLatest() {
		return latest;
	}

	public void setLatest(int latest) {
		this.latest = latest;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}
	
}
