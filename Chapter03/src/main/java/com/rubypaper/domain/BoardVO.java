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
@Builder //Builder를 쓰려면 AllArgs생성자가 꼭 필요(NoArgs는 빌더에서 필요한건 xx)
@NoArgsConstructor
@AllArgsConstructor 

public class BoardVO {

	private int seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default
	private Date createDate = new Date();
	private int cnt;
}
