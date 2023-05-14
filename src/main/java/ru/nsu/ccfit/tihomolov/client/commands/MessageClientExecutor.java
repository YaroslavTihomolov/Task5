package ru.nsu.ccfit.tihomolov.client.commands;

import ru.nsu.ccfit.tihomolov.client.controller.Controller;
import ru.nsu.ccfit.tihomolov.univeral_classes.CommandAnnotation;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

@CommandAnnotation
public class MessageClientExecutor implements CommandClientExecutor {

    @Override
    public void execute(Controller controller, Message message) {
        controller.getView().printMessages(message);
    }
}
