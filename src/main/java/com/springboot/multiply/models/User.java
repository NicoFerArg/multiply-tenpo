package com.springboot.multiply.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="USUARIO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="USERNAME")
    @NotBlank
    private String username;

    @Column(name="PASSWORD")
    @NotBlank
    private String password;

    @Column(name="MAIL")
    @NotBlank
    private String mail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
