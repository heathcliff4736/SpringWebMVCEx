package com.ssg.springwebmvcboard.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long board_Id;       // PK

    @NotEmpty
    private String title;       // 게시글 제목

    @NotEmpty
    private String content;     // 게시글 내용

    @NotEmpty
    private String writer;      // 작성자

    private String passphrase;  // 비밀번호
    private Long viewcount;     // 조회수
    private Long likecount;      // 좋아요 수
    private String filePath;    // 업로드 파일 경로
    private String originalName;// 업로드 파일 원본 이름
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created_At; // 생성일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updated_At; // 수정일
}
