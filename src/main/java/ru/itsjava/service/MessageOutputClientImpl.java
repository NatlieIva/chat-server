package ru.itsjava.service;

import java.io.OutputStream;
import java.io.PrintWriter;

public class MessageOutputClientImpl implements MessageOutputClient {

    private final PrintWriter writer;


    public MessageOutputClientImpl(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    @Override
    public void printMessage(String message) {
        writer.println(message);
        writer.flush();
    }
}
