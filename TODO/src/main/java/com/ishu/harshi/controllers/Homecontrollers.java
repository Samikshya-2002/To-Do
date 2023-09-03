package com.ishu.harshi.controllers;

import com.ishu.harshi.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Homecontrollers {

    @Autowired
    private TodoServices todoServices;
    @GetMapping("/")

    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("index");
        modelAndView.addObject("todoitems", todoServices.getAll());
        return modelAndView;
    }
}
