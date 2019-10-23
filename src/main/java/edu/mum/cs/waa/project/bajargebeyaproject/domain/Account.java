package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//@Entity
@Embeddable
@Data
public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @NotBlank
    private String cardType;
    @NotBlank
    private String number;
    @Size(min=3, max = 3)
    private String csv;
    @NotBlank
    private LocalDate expiryDate;
}
