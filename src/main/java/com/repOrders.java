package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class repOrders implements Repository<Orders>{
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
        sql_request="insert into orders values("+orders.getId()+", "+orders.isGarant()+", "+orders.getDate()+", "+orders.getPhone()+", "+orders.getTovar_id().getId()+", "+orders.getClient_name()+");";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rRemove(Orders orders) {
        sql_request="delete from orders where id="+orders.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Orders orders,int id,String column) {
        String change_column="";
        switch(column){
            case "id":{change_column+=orders.getId();break;}
            case "garant":{change_column+=orders.isGarant();break;}
            case "date":{change_column+=orders.getDate();break;}
            case "phone":{change_column+=orders.getPhone();break;}
            case "tovar_id":{change_column+=orders.getTovar_id().getId();break;}
            case "client_name":{change_column+=orders.getClient_name();break;}
        }
        sql_request="update orders set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
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

