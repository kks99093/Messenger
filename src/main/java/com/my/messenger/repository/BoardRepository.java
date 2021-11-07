package com.my.messenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.messenger.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	List<Board> findByCategoryOrderByBoardPkDesc(int category);
	
	Board findByBoardPk(int boardPk);

}
