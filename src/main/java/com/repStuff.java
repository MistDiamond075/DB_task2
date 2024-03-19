package com;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class repStuff implements Repository<Stuff>{
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
    public void rAdd(Stuff stuff) {
        sql_request="insert into stuff values("+stuff.getId()+", "+stuff.getFullname()+", "+stuff.getProfession()+");";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rRemove(Stuff stuff) {
        sql_request="delete from stuff where id="+stuff.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Stuff stuff,int id,String column) {
        String change_column="";
        switch(column){
            case "id":{change_column+=stuff.getId();break;}
            case "fullname":{change_column+=stuff.getFullname();break;}
            case "profession":{change_column+=stuff.getProfession();break;}
        }
        sql_request="update stuff set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
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

