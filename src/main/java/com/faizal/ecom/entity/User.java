package com.faizal.ecom.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;


@Data
@Getter
@Setter
@ToString
@Entity
public class User extends AbstractDomain {

    @Id
    private String uid;
    private String firstName;
    private String lastName;
    private String phone;
    private int verificationOTP;

    @OneToMany(mappedBy = "user")
    private List<Address> address;

    @OneToMany(mappedBy = "user")
    private List<Password> password;

    public User () {
        this.setCreatedDate(Calendar.getInstance(TimeZone.getDefault()));
        this.uid = UUID.randomUUID().toString();
        this.setStatus(Status.DEACTIVE);
    }

    public User(String firstName, String lastName, String phone) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
