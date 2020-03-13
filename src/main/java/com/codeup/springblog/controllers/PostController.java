package com.codeup.springblog.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String index(){
        return "This is home to see posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost(@PathVariable long id, Model model){
        return "This is to see posts id";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost(@PathVariable long id, Model model){
        return "This is create posts";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost(@ModelAttribute Model model){
        return "This is submit posts";
    }
}
