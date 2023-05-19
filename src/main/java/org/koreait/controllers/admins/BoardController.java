package org.koreait.controllers.admins;

import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("AdminBoardController")
@RequestMapping("/admin/board")
public class BoardController {
    /**
     * 게시판 목록
     *
     * @return
     */
    @GetMapping
    public String index(Model model) {
        commonProcess(model);

        return "admin/board/index";
    }

    private void commonProcess(Model model) {
        // 서브 메뉴 처리
        List<MenuDetail> submenus = Menus.gets("board");
        model.addAttribute("submenus", submenus);
    }
}
