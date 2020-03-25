package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepo;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class PostController {

//-------------------------------------BELOW ARE FROM THE REPO/JDA EXERCISE-------------------------------------

    private final PostRepo postDao;
    private final UserRepository userDao;

    public PostController(PostRepo postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.getId() == postDao.getOne(id).getUser().getId()) {
            Post postToEdit = postDao.getOne(id);
            model.addAttribute("post", postToEdit);
            return "posts/edit";
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post postToEdit = postDao.getOne(id);
        postToEdit.setTitle(title);
        postToEdit.setBody(body);
        postDao.save(postToEdit);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.getId() == postDao.getOne(id).getUser().getId()) {
            postDao.deleteById(id);
        } //can add an else notification if have time
        return "redirect:/posts";
    }

    @GetMapping("/posts/search")
    public String searchPost(Model model) {
        Post post = postDao.findByTitle("tangible");
        model.addAttribute("post", post);
        return "posts/search";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model, Principal principal) {
        String userName = "";
        if (principal != null) {
            userName = principal.getName();
        }
        model.addAttribute("userName", userName);
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreateForm() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser != null) {
            return "posts/create";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body) {
        Post createdPost = new Post();
        createdPost.setTitle(title);
        createdPost.setBody(body);
        //below is new with authentication exercise
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        createdPost.setUser(loggedInUser);
        //createdPost.setUser(userDao.getOne(1l)); //this was before creating dynamic user
        postDao.save(createdPost);
        return "redirect:/posts";
    }
}



