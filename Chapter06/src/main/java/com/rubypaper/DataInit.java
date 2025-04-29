package com.rubypaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Component
public class DataInit implements ApplicationRunner {

	@Autowired
	private BoardRepository boardRepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(int i = 1 ; i <= 20; i++) {
			boardRepo.save(Board.builder()
								.title("title"+i)
								.writer("writer")
								.content("content" + i)
								.build());
		}
	}

}
