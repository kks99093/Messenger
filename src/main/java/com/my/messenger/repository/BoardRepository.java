package com.my.messenger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.messenger.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	Page<Board> findByCategory(int category, Pageable pageable);
	
	Board findByBoardPk(int boardPk);

}
