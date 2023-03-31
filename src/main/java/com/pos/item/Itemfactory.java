package com.pos.item;
public class Itemfactory {
    public Item getItemCategory(String item_category){
        if("Beverage".equalsIgnoreCase(item_category)){
            return new Beverage();
        }
        if("Clothing".equalsIgnoreCase(item_category)){
            return new Clothing();
        }
        if("Electronic".equalsIgnoreCase(item_category)){
            return new Electronic();
        }
        if("Grocery".equalsIgnoreCase(item_category)){
            return new Grocery();
        }
        return null;
    }
}
