package com;

import jakarta.persistence.*;

@Entity
@Table(schema = "stuff")
@NamedQuery(name="stuff.getAll",query="select c from Stuff c")
public class Stuff {
    @Id
    @Column (name="id")
    private int id;
    @Column (name="fullname")
    private String fullname;
    @Column (name="profession")
    private String profession;

    public Stuff(){}

    public Stuff(int id, String fullname, String profession) {
        this.id = id;
        this.fullname = fullname;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
