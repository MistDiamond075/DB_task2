package com;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class repOrders implements Repository<Orders>{
    @PersistenceContext
    private EntityManager emngr =Persistence.createEntityManagerFactory("DBC").createEntityManager();
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private List<Orders> ordersList;

    public  repOrders(){
        sql_request="";
        ordersList=new ArrayList<>();
        dbconn=new DB_connection();
        if(!dbconn.isConnected())System.exit(0);
        stmt=dbconn.getStatement();
    }

    @Override
    public void rAdd(Orders orders) {
        EntityTransaction et= emngr.getTransaction();
        et.begin();
        emngr.persist(orders);
        et.commit();
        sql_request="";
    }

    @Override
    @Transactional
    public void rRemove(Orders orders) {
        Orders rmvorders = emngr.find(Orders.class, orders.getId());
        if (rmvorders != null) {
            sql_request = "delete from orders where id=" + orders.getId() + ";";
            try {
                stmt.executeUpdate(sql_request);
                stmt.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
        sql_request = "";
    }

    @Override
    @Transactional
    public void rUpdate(Orders orders,int id,String column) {
        EntityTransaction et= emngr.getTransaction();
        Orders updtorders= emngr.merge(orders);
        sql_request="";
    }

    @Override
    public List<Orders> rList() {
        ResultSet res= null;
        int t=0;
        repTovar rTovar=new repTovar();
        //LinkedList<Tovar> tovarList=rTovar.rList();
            ordersList= emngr.createNamedQuery("orders.getAll",Orders.class).getResultList();
        if(!ordersList.isEmpty()){return ordersList;}
        return null;
    }
}

