package example.controller;

import example.entity.KeyDictionary;
import example.service.DictionaryService;
import example.validator.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
public class MyController {
    private final DictionaryService dictionaryService;
    private final MyValidator validator;

    @Autowired
    public MyController(DictionaryService dictionaryService, MyValidator validator) {
        this.dictionaryService = dictionaryService;
        this.validator = validator;
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
    @GetMapping("/add/{flag}")
    public String add(Model model, @PathVariable("flag") int flag) {
        KeyDictionary key = new KeyDictionary();
        key.setFlag(flag);
        model.addAttribute("addKey", key);
        return "add";
    }
    @PostMapping("/add/{flag}")
    public String add(@ModelAttribute("addKey") KeyDictionary keyDictionary, @RequestParam("value") String value,
                      BindingResult bindingResult, Model model, SessionStatus status) {
        validator.validate(keyDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("addKey", keyDictionary);
            return "add";
        }else
            status.setComplete();

        if (keyDictionary.getFlag() == 0)
            dictionaryService.saveOrUpdateKeyChar(keyDictionary, value);
        if (keyDictionary.getFlag() == 1)
            dictionaryService.saveOrUpdateKeyNum(keyDictionary, value);

        return "redirect:/home";
    }
    @GetMapping("/search/removeValue/{id}")
    public String removeValue(@PathVariable("id") int id) {
        dictionaryService.removeValue(id);
        return "redirect:/";
    }
    @GetMapping("/update/{name}")
    public String update(@PathVariable("name") String name, Model model) {
        KeyDictionary keyByName = dictionaryService.getKeyByName(name);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + keyByName.getFlag());
        model.addAttribute("updateKey", keyByName);
        return "update";
    }
    @PostMapping("/update/{name}")
    public String update(@ModelAttribute("updateKey") KeyDictionary keyDictionary, @RequestParam("value") String value,
                         BindingResult bindingResult, Model model, SessionStatus status) {
        validator.validate(keyDictionary, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateKey", keyDictionary);
            return "update";
        }else
            status.setComplete();

        if (keyDictionary.getFlag() == 0)
            dictionaryService.saveOrUpdateKeyChar(keyDictionary, value);
        if (keyDictionary.getFlag() == 1)
            dictionaryService.saveOrUpdateKeyNum(keyDictionary, value);

        return "redirect:/home";
    }
}
