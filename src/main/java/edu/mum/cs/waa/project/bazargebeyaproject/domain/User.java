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

    //todo: making address class embedded

//    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "bill_city")),
            @AttributeOverride(name = "country", column = @Column(name = "bill_country")),
            @AttributeOverride(name = "state", column = @Column(name = "bill_state")),
            @AttributeOverride(name = "street", column = @Column(name = "bill_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "bill_zip"))
    })
    private Address billingAddress;
//    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "mail_city")),
            @AttributeOverride(name = "country", column = @Column(name = "mail_country")),
            @AttributeOverride(name = "state", column = @Column(name = "mail_state")),
            @AttributeOverride(name = "street", column = @Column(name = "mail_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "mail_zip"))
    })
    private Address mailingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications;
}
