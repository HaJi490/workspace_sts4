package com.rubypaper.service;

import java.util.List;

import com.rubypaper.domain.Board;

public interface BoardService {

	List<Board> getBoardList();

	Board getBoard(Board board);

	void insertBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);

}