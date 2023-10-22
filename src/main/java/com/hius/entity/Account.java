package com.hius.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Account {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pass")
    private String pass;
}
