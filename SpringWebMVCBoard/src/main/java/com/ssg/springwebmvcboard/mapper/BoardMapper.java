package com.ssg.springwebmvcboard.mapper;

import com.ssg.springwebmvcboard.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insert(BoardVO boardVO);

    List<BoardVO> selectAll();

    BoardVO selectOne(Long board_Id);

    void delete(Long board_Id);

    void update(BoardVO boardVO);

    void viewCountUpdate(Long board_Id);

}
