package com.solution.solutionapi;

import com.solution.solutionapi.model.Stories;
import com.solution.solutionapi.repo.StoriesRepo;
import com.solution.solutionapi.utils.DataOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SolutionApiApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SolutionApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}


}
