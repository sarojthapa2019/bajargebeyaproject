package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}
