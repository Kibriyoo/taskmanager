package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;


@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/save")
    public Task saveTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/all")
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}