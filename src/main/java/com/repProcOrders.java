package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class repProcOrders implements Repository<Processing_orders>{
    private EntityManager emngr = Persistence.createEntityManagerFactory("DBC").createEntityManager();
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private List<Processing_orders> prordersList;

    public repProcOrders(){
        sql_request="";
        prordersList=new ArrayList<>();
        dbconn=new DB_connection();
        if(!dbconn.isConnected())System.exit(0);
        stmt=dbconn.getStatement();
    }

    @Override
    public void rAdd(Processing_orders prorders) {
        sql_request="insert into processing_prorders values("+prorders.getId()+", "+prorders.getRepair_type()+", "+prorders.getCost()+", "+prorders.getRepair_date()+", "+prorders.isClient_msg()+", "+prorders.getGet_tovar_date()+", "+prorders.getPayment()
                +", "+prorders.getOrders_id()+", "+prorders.getOrders_tovar_id()+", "+prorders.getStuff_id()+");";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rRemove(Processing_orders prorders) {
        sql_request="delete from processing_orders where id="+prorders.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Processing_orders prorders,int id,String column) {
        String change_column="";
        switch(column){
            case "id":{change_column+=prorders.getId();break;}
            case "repair_type":{change_column+=prorders.getRepair_type();break;}
            case "cost":{change_column+=prorders.getCost();break;}
            case "repair_date":{change_column+=prorders.getRepair_date();break;}
            case "client_msg":{change_column+=prorders.isClient_msg();break;}
            case "payment":{change_column+=prorders.getPayment();break;}
            case "orders_id":{change_column+=prorders.getOrders_id().getId();break;}
            case "orders_tovar_id":{change_column+=prorders.getOrders_tovar_id().getId();break;}
            case "stuff_id":{change_column+=prorders.getStuff_id().getId();break;}
        }
        sql_request="update processing_orders set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public List<Processing_orders> rList() {
        ResultSet res= null;
        prordersList= emngr.createNamedQuery("processing_orders.getAll",Processing_orders.class).getResultList();
        if(!prordersList.isEmpty()){return prordersList;}
        return null;
    }
}

