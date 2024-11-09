module org.populaire.adjacenciesgen {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens org.populaire.adjacenciesgen to javafx.fxml;
    exports org.populaire.adjacenciesgen;
    exports org.populaire.adjacenciesgen.service;
    opens org.populaire.adjacenciesgen.service to javafx.fxml;
    exports org.populaire.adjacenciesgen.controller;
    opens org.populaire.adjacenciesgen.controller to javafx.fxml;
}