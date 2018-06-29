package example.controller;

import example.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
    private final DictionaryService dictionaryService;

    @Autowired
    public MyController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @RequestMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("listKeyChar", dictionaryService.keyListChar());
        model.addAttribute("listKeyNum", dictionaryService.keyListNumber());
        return "home";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        dictionaryService.remove(id);
        return "redirect:/home";
    }
}
