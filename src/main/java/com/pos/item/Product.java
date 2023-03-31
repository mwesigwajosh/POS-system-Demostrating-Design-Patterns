package com.pos.item;
public class Product{
    private String name;
    private int id;
    private String category;
    private int price;
    public Product(String name, int id, String Category, int price){
        this.name = name;
        this.id = id;
        this.category = category;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getCategory(){
        return this.category;
    }
    
    public int getPrice(){
        return this.price;
    }
}