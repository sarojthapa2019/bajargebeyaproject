package edu.mum.cs.waa.project.bajargebeyaproject.domain;

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
    private String actionUrl;
    private LocalDate date;
    private Integer priority;
    @ManyToMany(mappedBy = "notifications")
    private List<User> receivers;
}
