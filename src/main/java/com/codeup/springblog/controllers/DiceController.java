package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String showDicePage(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/n")
    public String showResult(@RequestParam int num, Model model){
        int random = (int) (Math.random() * 6) + 1;
        model.addAttribute("random", random);
        model.addAttribute("num", num);

        return "roll-dice";
    }
}