package ru.nsu.ccfit.tihomolov.server.command;

import ru.nsu.ccfit.tihomolov.server.Server;
import ru.nsu.ccfit.tihomolov.univeral_classes.CommandAnnotation;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

@CommandAnnotation
public class MessageServerExecutor implements CommandServerExecutor {

    @Override
    public void execute(Server server, Message message) {
        server.getKernelServer().addMessage(server.getUserName(), String.valueOf(message.getContent()));
    }
}
