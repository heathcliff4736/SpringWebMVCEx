package com.ssg.springwebmvcboard.service;

import com.ssg.springwebmvcboard.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    void register(BoardDTO boardDTO);

    List<BoardDTO> getAll();

    BoardDTO getOne(Long board_Id);

    void remove(Long board_Id);

    void modify(BoardDTO boardDTO);

    void viewCountUpdate(Long board_Id);
}
