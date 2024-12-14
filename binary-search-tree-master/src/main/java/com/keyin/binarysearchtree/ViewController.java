package com.keyin.binarysearchtree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "enter-numbers";
    }
}
