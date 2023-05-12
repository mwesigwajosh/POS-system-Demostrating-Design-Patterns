package com.pos.barcodescanner;
public interface BarcodescannerInterface {
    void registerObserver(Observercart observer);
    void notifyObserver(int productCode);
}

