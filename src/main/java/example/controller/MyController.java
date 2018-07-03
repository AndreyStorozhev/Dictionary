package example.controller;

import example.entity.KeyDictionary;
import example.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(value = "/remove/{id}")
    @ResponseBody
    public List<KeyDictionary> remove(@PathVariable("id") int id) {
        dictionaryService.remove(id);
        return dictionaryService.keyListChar();
    }
    @RequestMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("key", dictionaryService.getKeyByName(name));
        return "search";
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("addKeyChar", new KeyDictionary());
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("addKeyChar") KeyDictionary keyDictionary, @RequestParam("value") String value) {
        System.out.println(value);
        dictionaryService.saveOrUpdateKeyChar(keyDictionary, value);
        return "redirect:/home";
    }
}
