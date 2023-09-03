package com.ishu.harshi.controllers;

import com.ishu.harshi.model.Todoitem;
import com.ishu.harshi.services.TodoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Todoformcontrollers {

    @Autowired
    private TodoServices todoServices;

    @GetMapping("/create-todo")
    public String showCreateForm(Todoitem todoitem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid Todoitem todoitem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-todo-item";
        }
        Todoitem item = new Todoitem();
        item.setDescription(todoitem.getDescription());
//        item.setComplete(todoitem.getComplete());
        item.setComplete(false); // You can change this as needed.

        todoServices.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        Todoitem todoitem = todoServices
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todoitem id: " + id + " not found"));

        todoServices.delete(todoitem);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Todoitem todoitem = todoServices
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todoitem id: " + id + " not found"));

        model.addAttribute("todo", todoitem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid Todoitem todoitem, BindingResult result, Model model) {
        Todoitem item = todoServices
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todoitem id: " + id + " not found"));

        item.setComplete(todoitem.getComplete());
        item.setDescription(todoitem.getDescription());

        todoServices.save(item);

        return "redirect:/";
    }
}