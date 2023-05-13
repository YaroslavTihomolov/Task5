package ru.nsu.ccfit.tihomolov.server;

import lombok.Getter;

import java.io.Serializable;

public class Message implements Serializable {
    @Getter
    private final String sender;

    @Getter
    private final String content;

    public Message(final String sender, final String content) {
        this.sender = sender;
        this.content = content;
    }
}
