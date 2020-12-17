package ru.itsjava;

import ru.itsjava.util.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(Settings.getValue("PORT")));

        System.out.println("==Server starts==");

        while (true) {
            Socket socket = serverSocket.accept();

            if (socket != null) {
                System.out.println("==Client connected==");

                BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String input;

                while ((input = clientReader.readLine()) != null){
                    System.out.println(input);
                }
            }
        }
    }
}
