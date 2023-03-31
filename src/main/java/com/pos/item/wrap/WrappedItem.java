package com.pos.item.wrap;
import com.pos.item.Item;

public class WrappedItem extends ItemWrapping {
    private final int WrapPrice = 200;
    private int new_price;
    public WrappedItem(Item item){
        super(item);
    }
    
    @Override
    public int getPrice() {
        new_price = item.getPrice() + WrapPrice;
        return new_price;
    }
}
