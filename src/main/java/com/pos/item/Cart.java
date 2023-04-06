package com.pos.item;

public class Cart implements Subject {
    private Observer observer;
    String message;

    @Override
    public void registerObserver(Observer observer_name) {
        observer = observer_name;
    }

    @Override
    public void removeObserver() {
        observer = null;
    }
        
    @Override
    public String notifyObserver(){
            message = observer.update();
            return message;
    }
    
}
