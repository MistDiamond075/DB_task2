package com;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DB_management {
    protected Statement stmt=null;
    private List<Processing_orders> proc_ordersList;
    private List<Orders> ordersList;
    private List<Stuff> stuffList;
    private List<Tovar> tovarList;
    public DB_management(){
        DB_connection db_connection=new DB_connection();
        if(!db_connection.isConnected())System.exit(0);
        proc_ordersList=new ArrayList<>();
        ordersList=new ArrayList<>();
        stuffList=new ArrayList<>();
        tovarList=new ArrayList<>();
        stmt=db_connection.getStatement();
    }
    private void getDataFromDB(){
        repTovar rTovar=new repTovar();
        repStuff rStuff=new repStuff();
        repOrders rOrders=new repOrders();
        repProcOrders rProrders=new repProcOrders();
        ordersList=rOrders.rList();
        stuffList=rStuff.rList();
        tovarList=rTovar.rList();
        proc_ordersList=rProrders.rList();
    }
    public void shDBdata(){
        getDataFromDB();
        System.out.println("tovar->orders");
        System.out.println("==================");
        for(Tovar tovar:tovarList){
            System.out.println(tovar.getId()+" "+tovar.getName()+" "+tovar.getGarant()+" "+tovar.getFirm_name()+" "+tovar.getModel()+" "+tovar.getProperties()+" "+tovar.isImage()+" | ");
        }
        System.out.println("==================");
        for(Orders orders: ordersList){
            System.out.println(orders.getId()+" "+orders.isGarant()+" "+orders.getPhone()+" "+orders.getClient_name()+" "+orders.getDate()+" "+orders.getTovar_id().getId());
        }
        System.out.println("==================");
        System.out.println("stuff,orders->processing_orders");
        System.out.println("==================");
        for(Stuff stuff:stuffList){
            System.out.println(stuff.getId()+" "+stuff.getFullname()+" "+stuff.getProfession()+" | ");
        }
        System.out.println("==================");
        for(Orders orders:ordersList){
            System.out.println(orders.getId()+" "+orders.isGarant()+" "+orders.getPhone()+" "+orders.getClient_name()+" "+orders.getDate()+" "+orders.getTovar_id().getId());
        }
        System.out.println("==================");
        for(Processing_orders prord:proc_ordersList){
            System.out.println(prord.getId()+" "+prord.getCost()+" "+prord.getPayment()+" "+prord.getOrders_id().getId()+" "+prord.getOrders_tovar_id().getTovar_id().getId()+" "+prord.getRepair_date()+" "+prord.getRepair_type()+" "+
                    prord.getStuff_id().getId()+" "+prord.isClient_msg()+" "+prord.getGet_tovar_date());
        }
    }
}
