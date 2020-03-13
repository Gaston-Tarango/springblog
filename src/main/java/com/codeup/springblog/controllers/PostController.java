package com.codeup.springblog.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class PostController {
    @GetMapping("/posts")
    public String index(Model model){
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model){
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost(@PathVariable long id, Model model){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitPost(@ModelAttribute Model model){
        return "redirect:/home";
    }
}
