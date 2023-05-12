package com.pos.barcodescanner;
public class Observercart implements ObserverInterface {
    int productCode;
    @Override
    public void update(int productCode){
        this.productCode = productCode;   
    }
    
    @Override
    public int getproductCode(){
        return this.productCode;
    }
}
