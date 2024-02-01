package serverAPP;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        Controller controller = new Controller();

        // Conteneur pour composants

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        // Composants

        Button btn = new Button();
        btn.setText("Se connecter au serveur >");

        // Gestionnaire d'évenements du boutton

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                controller.createClient();
            }
        });

        
        root.getChildren().add(btn);

        // Création d'une scène

        Scene scene = new Scene(root, 400, 550);

        // Définir la scène sur la fenêtre principale 

        primaryStage.setTitle("Oscar CHENE - ChatAPP");
        primaryStage.setScene(scene);

        // Affichage la fenêtre principale

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
