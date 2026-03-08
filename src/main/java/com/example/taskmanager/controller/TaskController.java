package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    // Changed to PostMapping to match your HTML form's method="post"
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }

    // 1. ADD THIS: Method to show the edit form with existing task data
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        return "edit-task"; // This must match your edit-task.html filename
    }

    // 2. ADD THIS: Method to process the update
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id, @ModelAttribute("task") Task task) {
        task.setId(id); // Ensures JPA updates the existing record instead of creating a new one
        taskRepository.save(task);
        return "redirect:/";
    }
}