package com.pos.item.wrap;
import com.pos.item.Item;

public abstract class ItemWrapping implements Item {
    protected Item item;
    
    public ItemWrapping (Item item){
        this.item = item;
    }

    @Override
    public String getname() {
        return item.getname();
    }

    @Override
    public String getCategory() {
        return item.getCategory();
    }

    @Override
    public int getID() {
       return item.getID();
    }

    @Override
    public int getPrice() {
        return item.getPrice();
    }
    
    public int getQuantity(){
        return item.getQuantity();
    }

    @Override
    public void builditem(String name, int id, int price, int quantity) {
        item.builditem(name, id, price, quantity);
    }
}
