package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int num1, @PathVariable int num2){
        return num1 + num2;
    }

    @GetMapping("/subtract/{num1}/and/{num2}")
    @ResponseBody
    public int subtract(@PathVariable int num1, @PathVariable int num2){
        return num1 - num2;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public int multiply(@PathVariable int num1, @PathVariable int num2){
        return num1 * num2;
    }

    @GetMapping("/divide/{num1}/and/{num2}")
    @ResponseBody
    public int divide(@PathVariable int num1, @PathVariable int num2){
        if (num2 == 0) {
            throw new ArithmeticException(("Learn to MATH"));
        }
        return num1 / num2;
    }
}
