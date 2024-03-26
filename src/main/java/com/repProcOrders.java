package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class repProcOrders implements Repository<Processing_orders>{
    @PersistenceContext
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
        EntityTransaction et= emngr.getTransaction();
        et.begin();
        emngr.persist(prorders);
        et.commit();
        sql_request="";
    }

    @Override
    @Transactional
    public void rRemove(Processing_orders prorders) {
        Processing_orders rmvprorders=emngr.find(Processing_orders.class,prorders.getId());
        if(rmvprorders!=null) {
            sql_request = "delete from processing_orders where id=" + prorders.getId() + ";";
            try {
                stmt.executeUpdate(sql_request);
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        sql_request="";
    }

    @Override
    @Transactional
    public void rUpdate(Processing_orders prorders,int id,String column) {
        EntityTransaction et= emngr.getTransaction();
        Processing_orders upddtprorders= emngr.merge(prorders);
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

