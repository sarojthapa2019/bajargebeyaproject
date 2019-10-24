package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

//@Entity
@Embeddable
@Data
public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String cardType;
    private String number;
    private String csv;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiryDate;
}
