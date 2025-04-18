package com.rubypaper.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default //builder에 디폴토값을 주겠다
	private Date createDate = new Date();
	private int cnt;

}
