package ru.itsjava.service;

import lombok.SneakyThrows;
import ru.itsjava.util.Settings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements Server, Observable {
    private final List<Observer> observerList = new ArrayList<>();


    @SneakyThrows
    @Override
    public void start() {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(Settings.getValue("PORT")));

        System.out.println("==Server starts==");

        while (true) {
            Socket socket = serverSocket.accept();

            if (socket != null) {
                System.out.println("==Client connected==");
                ClientRunnable clientRunnable = new ClientRunnable(socket, this);
                observerList.add(clientRunnable);
                new Thread(clientRunnable).start();
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observerList) {
            observer.notify(message);
        }

    }

    @Override
    public void notifyObserversExceptOne(Observer observerExcept, String message) {
        for (Observer observer : observerList){
            if (!observerExcept.equals(observer)){
                observer.notify(message);
            }
        }
    }
}
