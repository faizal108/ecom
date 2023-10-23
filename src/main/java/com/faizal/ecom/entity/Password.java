package com.faizal.ecom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@Entity
public class Password extends AbstractDomain{

    @Id
    private String psw_id;
    private String userPassword;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    @JsonBackReference
    private User user;

    public Password(){
        this.psw_id = UUID.randomUUID().toString();
        this.setStatus(Status.ACTIVE);
    }
    public Password(String password, User user) {
        this();
        this.userPassword = password;
        this.user = user;
    }
}
