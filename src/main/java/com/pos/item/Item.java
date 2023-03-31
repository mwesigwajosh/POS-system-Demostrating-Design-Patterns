package com.pos.item;
public interface Item {
    public void builditem(String name, int id, int price);
    public String getname();
    public String getCategory();
    public int getID();
    public int getPrice();
}
