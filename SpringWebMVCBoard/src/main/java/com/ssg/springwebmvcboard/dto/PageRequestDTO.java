package com.ssg.springwebmvcboard.dto;

import lombok.*;

@ToString
@Data
@Builder
public class PageRequestDTO {
    private int page = 1;        // 현재 페이지
    private int size = 10;       // 페이지당 글 수
    private String keyword;      // 검색 키워드 (선택)

    // 계산용
    public int getOffset() {
        return (page - 1) * size;
    }
}
