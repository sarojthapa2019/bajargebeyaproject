package edu.mum.cs.waa.project.bajargebeyaproject.domain;
import lombok.Data;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;
    @Size(min=2, max=40)
    private String lastName;

//    @OneToOne(cascade = CascadeType.ALL)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "bill_city")),
            @AttributeOverride(name = "country", column = @Column(name = "bill_country")),
            @AttributeOverride(name = "state", column = @Column(name = "bill_state")),
            @AttributeOverride(name = "street", column = @Column(name = "bill_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "bill_zip"))
    })
    @Valid
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
    @Valid
    private Address mailingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    @NotBlank
    @Email
    private String email;
    private String password;
    //@OneToOne(cascade = CascadeType.ALL)
    @Embedded
    private Account account;
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Notification> notifications = new ArrayList<>();
    public User addNotification(Notification n) {
        this.notifications.add(n);
        return this;
    }
}