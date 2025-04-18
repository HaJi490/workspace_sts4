package com.rubypaper.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class BoardController {
	//데이터를 저장하는 객체(DTO,VO)는 빈(bean)객체로 안만듦
	
	@GetMapping("/hello")
	public String hello(String name) {
		log.trace("/hello 호출");
		return "Hello : " + name;
	}

	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		//new를 이용해 생성할 필요없이 builder로 생성
		return BoardVO.builder().writer("테스터").build();
	}
	
}
