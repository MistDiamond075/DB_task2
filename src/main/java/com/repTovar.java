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

public class repTovar implements Repository<Tovar>{
    @PersistenceContext
    private EntityManager emngr = Persistence.createEntityManagerFactory("DBC").createEntityManager();
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private List<Tovar> tovarList;

    public repTovar(){
        sql_request="";
        tovarList=new ArrayList<>();
        dbconn=new DB_connection();
        if(!dbconn.isConnected())System.exit(0);
        stmt=dbconn.getStatement();
    }
    @Override
    public void rAdd(Tovar tovar) {
       EntityTransaction et=emngr.getTransaction();
       et.begin();
       emngr.persist(tovar);
       et.commit();
        sql_request="";
    }

    @Override
    @Transactional
    public void rRemove(Tovar tovar) {
        Tovar rmvtovar=emngr.find(Tovar.class,tovar.getId());
        if(rmvtovar!=null) {
            sql_request = "delete from tovar where id=" + tovar.getId() + ";";
            try {
                stmt.executeUpdate(sql_request);
                stmt.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
            sql_request = "";
        }
    }

    @Override
    @Transactional
    public void rUpdate(Tovar tovar,int id,String column) {
        EntityTransaction et= emngr.getTransaction();
        Tovar updttovar= emngr.merge(tovar);
        sql_request="";
    }

    @Override
    public List<Tovar> rList() {
        ResultSet res= null;
        tovarList= emngr.createNamedQuery("tovar.getAll",Tovar.class).getResultList();
        if(!tovarList.isEmpty()){return tovarList;}
        return null;
    }
}

