package com.example.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.model.Task;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("tasks", service.getAllTasks());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/save")
    public String saveTask(Task task){
        service.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        service.deleteTask(id);
        return "redirect:/";
    }
}