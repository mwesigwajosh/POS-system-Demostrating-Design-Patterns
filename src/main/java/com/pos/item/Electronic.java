package com.pos.item;
public class Electronic implements Item {
    String Name;
    int ID;
    String category;
    int price;

    Electronic(){
        this.category = "Electronic";
    }
    public void builditem(String name, int id, int price){
        this.Name = name;
        this.ID = id;
        this.price= price;
    }
    public String getname(){
        return this.Name;
    }
    public int getPrice(){
        return this.price;
    }
    public String getCategory(){
        return this.category;
    }
    public int getID(){
        return this.ID;
    }
}
