package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest (properties = {"author.name=Grum", "author.age=45", "author.nation=Korea"})
public class PropertiesTest {
	
	@Autowired
	Environment enviroment;
	
	@Test
	public void testMethod() {
		System.out.println("이름 : " + enviroment.getProperty("author.name"));
		System.out.println("나이 : " + enviroment.getProperty("author.age"));
		System.out.println("국가 : " + enviroment.getProperty("author.nation"));
	}

}
