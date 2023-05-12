package com.pos.barcodescanner;
public class Barcodescanner implements BarcodescannerInterface{
    Observercart observer;
    @Override
    public void registerObserver(Observercart observer){
        this.observer = observer;
    }
    
    @Override
    public void notifyObserver(int productCode){
        observer.update(productCode);
    }
}
