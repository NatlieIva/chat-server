package ru.itsjava.service;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MessageInputClientImpl implements MessageInputClient {
    private final BufferedReader reader;

    public MessageInputClientImpl(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    @Override
    public String getMessage() {
        return reader.readLine();
    }
}
