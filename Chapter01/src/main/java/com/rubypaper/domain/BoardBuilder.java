package com.rubypaper.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString


//Builder 구조 =============================================================
BoardBuilder builder() {
	return new BoardBuilder();
}

public class BoardBuilder {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private int cnt;
	
	BoardBuilder seq(int seq) {
		this.seq = seq;
		return this;
	}
	BoardBuilder title(int title) {
		this.title = title;
		return this;
	}
	
	Boardbuild(){
		return Board( seq, title, writer, content, cnt);
	}

}
// ============================================================================
