package com.example.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // This will now store "Not Started", "In Progress", or "Done"
    private String status;

    // We use LocalDate so it maps correctly to the SQL DATE type
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}