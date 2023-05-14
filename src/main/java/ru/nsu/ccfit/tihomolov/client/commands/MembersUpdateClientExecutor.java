package ru.nsu.ccfit.tihomolov.client.commands;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.tihomolov.client.controller.Controller;
import ru.nsu.ccfit.tihomolov.univeral_classes.CommandAnnotation;
import ru.nsu.ccfit.tihomolov.univeral_classes.Message;

import java.util.List;

@CommandAnnotation
@Log4j2
public class MembersUpdateClientExecutor implements CommandClientExecutor{

    @Override
    public void execute(Controller controller, Message message) {
        log.info("Update chat members");
        controller.getView().updateMembers((List<String>) message.getContent());
    }
}
