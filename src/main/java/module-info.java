module org.populaire.adjacenciesgen {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.populaire.adjacenciesgen to javafx.fxml;
    exports org.populaire.adjacenciesgen;
}