package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    //todo: making address class transient

//    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    private Address billingAddress;
//    @OneToOne(cascade = CascadeType.ALL)
//    @Embedded
//    private Address mailingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications;
}
