package com.hius.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pass")
    private String pass;
}
