package ru.itsjava.service;

import java.net.Socket;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
    void notifyObserversExceptOne(Observer observerExcept, String message);
}
