//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

module ru.nsu.ccfit.tihomolov {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires log4j;
    requires org.jetbrains.annotations;

    requires org.apache.logging.log4j;

    exports ru.nsu.ccfit.tihomolov.client;
    exports ru.nsu.ccfit.tihomolov.server;

    opens ru.nsu.ccfit.tihomolov.client to
            javafx.fxml;

    exports ru.nsu.ccfit.tihomolov.client.view;
    opens ru.nsu.ccfit.tihomolov.client.view to javafx.fxml;
    exports ru.nsu.ccfit.tihomolov.client.controller;
    opens ru.nsu.ccfit.tihomolov.client.controller to javafx.fxml;
}
