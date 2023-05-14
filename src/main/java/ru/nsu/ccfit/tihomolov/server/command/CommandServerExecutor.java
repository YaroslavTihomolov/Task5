package ru.nsu.ccfit.tihomolov.server.command;

import ru.nsu.ccfit.tihomolov.univeral_classes.Message;
import ru.nsu.ccfit.tihomolov.server.Server;

public interface CommandServerExecutor {
    void execute(Server server, Message message);
}
