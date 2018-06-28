package example.controller;

import example.service.KeyService;
import example.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    private final KeyService keyService;
    private final ValueService valueService;

    @Autowired
    public MyController(KeyService keyService, ValueService valueService) {
        this.keyService = keyService;
        this.valueService = valueService;
    }

    @RequestMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("listKey", keyService.keyList());
        return "home";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        keyService.remove(id);
        return "redirect:/home";
    }
}
