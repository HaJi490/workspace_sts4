package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.rubypaper.domain.BoardVO;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
	// **2. 내장톰캣으로 테스트 **
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("/hello 테스트") //안하면 메서드명이 뜨는데 
	public void testHello() {
		String result = restTemplate.getForObject("/hello?name=둘리", String.class); //(url, 리턴타입)
		assertEquals("Hello : 둘리", result); //(예상결과, 실제결과) 비교
	}
	
	@Test
	@DisplayName("/getBoard 테스트")
	public void testGetBoard() {
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		assertEquals("테스터", board.getWriter());
	}

//	**1. boot제공 목업**
//	@SpringBootTest @AutoCongigureMockMvc
//	class______(){
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Test
//	public void testHello() throws Exception{
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//		.andExpect(status().isOk())
//		.andExpect((ResultMatcher) content().string("Hello : 둘리"))
//		.andDo(print()); 
//	}

}
