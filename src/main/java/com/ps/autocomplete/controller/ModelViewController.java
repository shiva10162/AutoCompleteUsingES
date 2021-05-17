package com.ps.autocomplete.controller;

import com.ps.autocomplete.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/", "/index" })
public class ModelViewController {

    @GetMapping
    public String main(Model model) {
        model.addAttribute("user", new User());
        return "start";
    }

}