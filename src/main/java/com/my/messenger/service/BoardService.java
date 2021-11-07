package com.my.messenger.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.messenger.model.dto.BoardDto;
import com.my.messenger.model.entity.Board;
import com.my.messenger.model.entity.UserInfo;
import com.my.messenger.repository.BoardRepository;
import com.my.messenger.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRep;
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	public void insBoard(Board board) {
		if(board.getBoardPk() == null) { //글쓰기
			boardRep.save(board);	
		}else { //글수정
			Board oriBoard = boardRep.findByBoardPk(board.getBoardPk());
			oriBoard.setTitle(board.getTitle());
			oriBoard.setContent(board.getContent());
			boardRep.save(oriBoard);
		}
		
		
		
			
		
	}
	
	public List<BoardDto> selBoardList(int category){
		List<Board> boardList = boardRep.findByCategoryOrderByBoardPkDesc(category);
		List<BoardDto> boardDtoList = new ArrayList<>();
		for(Board board : boardList) {			
			int userPk = board.getUserPk();	
			BoardDto boardDto = modelMapper.map(board, BoardDto.class);
			UserInfo userInfo = userRep.findByUserPk(userPk);
			boardDto.setUsername(userInfo.getUsername());			
			boardDtoList.add(boardDto);
			
		}
		
		return boardDtoList;
	}
	
	public BoardDto selBoard(int boardPk) {
		Board board = boardRep.findByBoardPk(boardPk);
		BoardDto boardDto = modelMapper.map(board, BoardDto.class);
		int userPk = board.getUserPk();	
		UserInfo userInfo = userRep.findByUserPk(userPk);
		boardDto.setUsername(userInfo.getUsername());		
		
		return boardDto;
	}
	
	public void delBoard(Board board) {
		boardRep.delete(board);
	}

}