package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String taskUrl;
    private LocalDate date;
    private String priority;
    @ManyToMany(mappedBy = "notifications")
    private List<User> receivers;
}
