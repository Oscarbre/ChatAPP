package serverAPP;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créez les composants de votre interface utilisateur
        Button btn = new Button();
        btn.setText("Envoyer >");

        // Ajoutez un gestionnaire d'événements au bouton
        btn.setOnAction(e -> System.out.println("Hello World!"));

        // Créez un conteneur pour vos composants (dans cet exemple, un StackPane)
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Créez une scène avec le conteneur
        Scene scene = new Scene(root, 400, 550);

        // Définissez la scène sur la fenêtre principale (primaryStage)
        primaryStage.setTitle("Oscar CHENE - ChatAPP");
        primaryStage.setScene(scene);

        // Affichez la fenêtre principale
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
