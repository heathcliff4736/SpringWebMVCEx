package com.ssg.springwebmvcboard.service;

import com.ssg.springwebmvcboard.domain.BoardVO;
import com.ssg.springwebmvcboard.dto.BoardDTO;
import com.ssg.springwebmvcboard.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(BoardDTO boardDTO) {
        log.info(modelMapper);

        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);

        log.info(boardVO);

        boardMapper.insert(boardVO);
    }

    @Override
    public List<BoardDTO> getAll() {
        List<BoardDTO> boardDTOList = boardMapper.selectAll().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return boardDTOList;
    }

    @Override
    public BoardDTO getOne(Long board_Id) {
        BoardVO boardVO = boardMapper.selectOne(board_Id);

        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);

        return boardDTO;
    }

    @Override
    public void remove(Long board_Id) {
        boardMapper.delete(board_Id);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        boardMapper.update(boardVO);
    }

    @Override
    public void viewCountUpdate(Long board_Id) {
        boardMapper.viewCountUpdate(board_Id);
    }
}
