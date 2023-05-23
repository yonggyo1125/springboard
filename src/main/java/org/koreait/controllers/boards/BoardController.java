package org.koreait.controllers.boards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    /**
     * 게시글 목록
     * @param bId
     * @return
     */
    @GetMapping("/list/{bId}")
    public String list(@PathVariable String bId) {

        return "board/list";
    }

    /**
     * 게시글 작성
     *
     * @param bId
     * @return
     */
    @GetMapping("/write/{bId}")
    public String write(@PathVariable String bId) {

        return "board/write";
    }

    /**
     * 게시글 수정
     * @param id
     * @return
     */
    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id) {

        return "board/update";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id) {

        return "board/view";
    }
}
