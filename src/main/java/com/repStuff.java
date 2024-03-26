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

public class repStuff implements Repository<Stuff>{
    @PersistenceContext
    private EntityManager emngr = Persistence.createEntityManagerFactory("DBC").createEntityManager();
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private List<Stuff> stuffList;

    public repStuff(){
        sql_request="";
        stuffList=new ArrayList<>();
        dbconn=new DB_connection();
        if(!dbconn.isConnected())System.exit(0);
        stmt=dbconn.getStatement();
    }
    @Override
    @Transactional
    public void rAdd(Stuff stuff) {
        EntityTransaction et= emngr.getTransaction();
        et.begin();
        emngr.persist(stuff);
        et.commit();
        sql_request="";
    }

    @Override
    @Transactional
    public void rRemove(Stuff stuff) {
        Stuff rmvstuff=emngr.find(Stuff.class,stuff.getId());
        if(rmvstuff!=null) {
            sql_request = "delete from stuff where id=" + stuff.getId() + ";";
            try {
                stmt.executeUpdate(sql_request);
                stmt.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
        sql_request="";
    }

    @Override
    public void rUpdate(Stuff stuff,int id,String column) {
       EntityTransaction et=emngr.getTransaction();
       Stuff updtstuff=emngr.merge(stuff);
        sql_request="";
    }

    @Override
    public List<Stuff> rList() {
        ResultSet res= null;
        stuffList= emngr.createNamedQuery("stuff.getAll",Stuff.class).getResultList();
        if(!stuffList.isEmpty()){return stuffList;}
        return null;
    }
}

