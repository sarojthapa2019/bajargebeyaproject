package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "bill_city")),
            @AttributeOverride(name = "country", column = @Column(name = "bill_country")),
            @AttributeOverride(name = "state", column = @Column(name = "bill_state")),
            @AttributeOverride(name = "street", column = @Column(name = "bill_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "bill_zip"))
    })
    private Address billingAddress;
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
    @Embedded
    private Account account;
    @ManyToMany(cascade = CascadeType.ALL)//, fetch=FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Notification> notifications;
    @Transient
    private transient int notificationCount;

    public int getNotificationCount(){
        return notifications.size();
    }
    public User(){
        notifications = new ArrayList<>();
    }

    public User addNotification(Notification n) {
        this.getNotifications().add(n);
        n.addReceiver(this);
        User u = new User();
        return this;
    }

    @Override
    public String toString(){
        return this.firstName;
    }
}
