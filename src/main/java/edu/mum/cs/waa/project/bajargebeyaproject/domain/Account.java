package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate expiryDate;
}
