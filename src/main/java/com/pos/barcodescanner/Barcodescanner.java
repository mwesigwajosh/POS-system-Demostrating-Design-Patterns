package com.pos.barcodescanner;
public class Barcodescanner {
    Observercart observer;
    public void registerObserver(Observercart observer){
        this.observer = observer;
    }
    
    public void notifyObserver(int productCode){
        observer.update(productCode);
    }
}
