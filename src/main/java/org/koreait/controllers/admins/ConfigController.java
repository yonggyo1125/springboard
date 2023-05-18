package org.koreait.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/config")
public class ConfigController {
    @GetMapping
    public String config(Model model) {
        commonProcess(model);

        return "admin/config";
    }

    @PostMapping
    public String configPs(Model model) {
        commonProcess(model);

        return "admin/config";
    }

    private void commonProcess(Model model) {
        String title = "사이트 설정";
        String menuCode = "config";
        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);
        model.addAttribute("menuCode", menuCode);
    }
}
