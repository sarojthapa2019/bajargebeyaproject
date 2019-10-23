package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Embeddable
public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @Size(min=2, max=2)
    private String state;
    private String zipCode;
    private String country;

}
