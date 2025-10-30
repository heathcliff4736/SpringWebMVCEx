package com.ssg.springwebmvcboard.mapper;

import com.ssg.springwebmvcboard.domain.BoardVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTests {

    // 인터페이스는 생성자가 필요 없으므로 required는 false로 지정
    @Autowired(required = false)
    private BoardMapper boardMapper;

    @Test
    public void testSelectAll() {
        List<BoardVO> boardVOList = boardMapper.selectAll();
        log.info(boardVOList);
    }

}
