package com;

import jakarta.persistence.*;

@Entity
@Table (name ="orders")
@NamedQuery(name="orders.getAll",query="select c from Orders c")
public class Orders {
    @Id
    @Column (name="id")
    private int id;
    @Column (name="garant")
    private boolean garant;
    @Column (name="date")
    private String date;
    @Column (name="phone")
    private int phone;
    @Column (name="client_name")
    private String client_name;
    @ManyToOne
    @JoinColumn (name ="tovar_id")
    private Tovar tovar_id;

    public Orders(int id, boolean garant, String date, int phone, String client_name, Tovar tovar_id) {
        this.id = id;
        this.garant = garant;
        this.date = date;
        this.phone = phone;
        this.client_name = client_name;
        this.tovar_id = tovar_id;
    }

    public Orders(){}

    public int getId() {
        return id;
    }

    public boolean isGarant() {
        return garant;
    }

    public void setGarant(boolean garant) {
        this.garant = garant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public Tovar getTovar_id() {
        return tovar_id;
    }

    public void setTovar_id(Tovar tovar_id) {
        this.tovar_id = tovar_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
