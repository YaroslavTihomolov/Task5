package ru.nsu.ccfit.tihomolov.client.commands;

import ru.nsu.ccfit.tihomolov.client.controller.Controller;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

public interface CommandClientExecutor {
    void execute(Controller controller, Message message);
}
