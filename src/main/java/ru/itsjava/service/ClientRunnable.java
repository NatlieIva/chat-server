package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.model.User;
import java.net.Socket;

@RequiredArgsConstructor
public class ClientRunnable implements Runnable, Observer {
    private final Socket socket;
    private final Observable server;
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        MessageInputClientImpl clientReader = new MessageInputClientImpl(socket.getInputStream());
        String clientMessage;
        authorization(clientReader);
        while ((clientMessage = clientReader.getMessage()) != null) {
            System.out.println(user.getName() + ":" + clientMessage);
            String message = user.getName() + ":" + clientMessage;
            server.notifyObserversExceptOne(this, message);
        }
    }

    @SneakyThrows
    public void authorization(MessageInputClientImpl clientReader) {
        String clientMessage = clientReader.getMessage();
        //!auth!login:password
        if (clientMessage.startsWith("!auth!")) {
            String[] loginAndPass = clientMessage.substring(6).split(":");
            user = new User(loginAndPass[0], loginAndPass[1]);
            notify("Successful authorisation!");
        }
    }

    @SneakyThrows
    @Override
    public void notify(String message) {
        MessageOutputClientImpl clientWriter = new MessageOutputClientImpl(socket.getOutputStream());
        clientWriter.printMessage(message);
    }
}
