package com.pos.barcodescanner;
public class Observercart {
    int productCode;
    void update(int productCode){
        this.productCode = productCode;   
    }
    
    public int getproductCode(){
        return this.productCode;
    }
}
