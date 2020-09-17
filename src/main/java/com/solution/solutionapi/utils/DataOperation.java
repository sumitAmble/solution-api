package com.solution.solutionapi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.solution.solutionapi.model.Comment;
import com.solution.solutionapi.model.Stories;

@Component
public class DataOperation {
	private final String storiesUrl = "https://hacker-news.firebaseio.com/v0/topstories.json";
	private final String baseUrl = "https://hacker-news.firebaseio.com/v0/item/";
	private final String urlTail = ".json";
	private final String userUrl = "https://hacker-news.firebaseio.com/v0/user/";

	@Autowired
	private RestTemplate restTemplate;


	public List<Stories> buildData() {
		List<Stories> list = firstStories();
		return list.parallelStream().map(a -> getCommnets(a)).collect(Collectors.toList());
	}

	public List<Stories> firstStories() {
		List<Stories> list =getStories().parallelStream().map(a ->getStory(a))
				.filter(s-> s.getScore()!=null).collect(Collectors.toList());
		list.sort((a, b) -> a.getScore() - b.getScore());
		Collections.reverse(list);
		return list.stream().limit(10).collect(Collectors.toList());
	}

	private List<Integer> getStories() {
		ResponseEntity<Integer[]> stories = restTemplate.getForEntity(storiesUrl, Integer[].class);
		return Arrays.asList(stories.getBody());
	}

	private Stories getStory(Integer num) {
		ResponseEntity<String> stories = restTemplate.getForEntity(baseUrl + num + urlTail, String.class);
		return deserialize(stories.getBody());
	}

	private Stories getCommnets(Stories stories) {
		List<Comment> list = new ArrayList<>();

		if(stories.getKids()!=null) {
			for (int a : stories.getKids()) {
				Comment c =findComments(a);
				if (c!=null) {
					list.add(findComments(a));
				}
			}
		}
		list= list.stream().map(s->
		{
			s.setStories(stories);
			return s;
		}).collect(Collectors.toList());
		stories.setComments(list);
		stories.setLatest(1);
		
		return stories;
	}

	public Comment findComments(int id) {
		ResponseEntity<String> comment = restTemplate.getForEntity(baseUrl + id + urlTail, String.class);
		Comment comment1=deserializeCom(comment.getBody());
		return getUserDetails(comment1);
	}

	public Comment getUserDetails(Comment comment1){
		if(comment1.getBy()!=null) {
			ResponseEntity<String> userDetails = restTemplate.getForEntity(userUrl + comment1.getBy() + urlTail, String.class);
			if (userDetails.getBody() != null) {
				JsonObject jsonObject = new JsonParser().parse(userDetails.getBody()).getAsJsonObject();
				if (jsonObject.get("created") != null) {
					comment1.setUserCreatedDate(new Date(jsonObject.get("created").getAsLong()));
				}
			}
		}
		return comment1;
	}
	private Stories deserialize(String value) {

		Stories stories = new Stories();
		if(value!=null) {
			JsonObject jsonObject = new JsonParser().parse(value).getAsJsonObject();
			stories.setId(jsonObject.get("id").getAsLong());
			stories.setBy(jsonObject.get("by").getAsString());
			if (jsonObject.get("kids") != null) {
				JsonArray array = jsonObject.get("kids").getAsJsonArray();
				int[] arr = new int[array.size()];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = array.get(i).getAsInt();
				}
				stories.setKids(arr);
			}

			stories.setScore(jsonObject.get("score").getAsInt());
			stories.setTime(new Date(jsonObject.get("time").getAsLong()));
			stories.setTitle(jsonObject.get("title").getAsString());
			stories.setType(jsonObject.get("type").getAsString());
			if (jsonObject.get("url") != null)
				stories.setUrl(jsonObject.get("url").getAsString());
		}
			return stories;

	}

	public Comment deserializeCom(String value) {
		Comment com = new Comment();
		if(value!=null) {
			JsonElement jsonNode = new JsonParser().parse(value);
			try {
				if (jsonNode != null && jsonNode.getAsJsonObject() != null) {
					JsonObject jsonObject = jsonNode.getAsJsonObject();
					com.setId(jsonObject.get("id").getAsLong());
					if (jsonObject.get("by") != null)
						com.setBy(jsonObject.get("by").getAsString());
					if (jsonObject.get("kids") != null) {
						JsonArray array = jsonObject.get("kids").getAsJsonArray();
						int[] arr = new int[array.size()];
						for (int i = 0; i < arr.length; i++) {
							arr[i] = array.get(i).getAsInt();
						}
						com.setKids(arr);
					}
					if (jsonObject.get("text") != null)
						com.setText(jsonObject.get("text").getAsString());
					if (jsonObject.get("time") != null)
						com.setTime(new Date(jsonObject.get("time").getAsLong()));
					if (jsonObject.get("type") != null)
						com.setType(jsonObject.get("type").getAsString());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return com;
	}
}
