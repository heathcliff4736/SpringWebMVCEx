package com.ssg.springwebmvcboard.controller;

import com.ssg.springwebmvcboard.dto.BoardDTO;
import com.ssg.springwebmvcboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void registerGet(){
        log.info("registerGet---------------------------------------------------");
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        log.info("registerPost----------------------------------------------------");

        if(bindingResult.hasErrors()){
            log.error("has errors......");
            log.error(bindingResult.getAllErrors().toString());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        log.info(boardDTO.toString());

        boardService.register(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Model model){
        log.info("list------------------------------------------------------");
        log.info(model.toString());
        model.addAttribute("boardDTOList", boardService.getAll());
    }

    @GetMapping({"/read","/modify"})
    public void read(Long board_Id, Model model){
        log.info("read------------------------------------------------------");
        boardService.viewCountUpdate(board_Id);
        BoardDTO boardDTO = boardService.getOne(board_Id);
        log.info(boardDTO.toString());
        model.addAttribute("boardDTO", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(Long board_Id, RedirectAttributes redirectAttributes) {
        log.info("remove--------------------------------------------------------");
        log.info("board_Id : "+board_Id);
        boardService.remove(board_Id);
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO,
                         BindingResult blindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("modify--------------------------------------------------------");
        if(blindingResult.hasErrors()){
            log.error(blindingResult.getAllErrors().toString());
            redirectAttributes.addFlashAttribute("errors", blindingResult.getAllErrors());
            redirectAttributes.addAttribute("boardDTO", boardDTO.getBoard_Id());
            return "redirect:/board/modify";
        }
        log.info(boardDTO);
        boardService.modify(boardDTO);
        return "redirect:/board/list";
    }


}
