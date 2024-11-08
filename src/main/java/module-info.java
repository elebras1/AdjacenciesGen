module org.populaire.adjacenciesgen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.populaire.adjacenciesgen to javafx.fxml;
    exports org.populaire.adjacenciesgen;
}