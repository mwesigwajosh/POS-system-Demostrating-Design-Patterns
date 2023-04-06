package com.pos.item;
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver();
    String notifyObserver();
}
