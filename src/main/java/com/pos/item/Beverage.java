package com.pos.item;
public class Beverage implements Item {
    String Name;
    int ID;
    String category;
    int price;
    int quantity;
    Beverage(){
        this.category = "Beverage";
    }
    public void builditem(String name, int id, int price, int quantity){
        this.Name = name;
        this.ID = id;
        this.price= price;
        this.quantity = quantity;
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
    public int getQuantity(){
        return this.quantity;
    }
}
