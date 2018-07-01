package example.controller;

import example.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody
    String remove(@PathVariable("id") int id) {
        dictionaryService.remove(id);
        return "";
    }
    @RequestMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("key", dictionaryService.getKeyByName(name));
        return "search";
    }
}
