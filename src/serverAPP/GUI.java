package serverAPP;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        Controller controller = new Controller();

        // Conteneur pour composants

        

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Composants

            // Titre

        Text sceneTitle = new Text("Bienvenue sur ChatAPP !");
        Text sceneSubtitle = new Text("Entrez votre nom d'utilisateur pour vous connecter au serveur");

        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

            // Login

        Label userName = new Label("Nom d'utilisateur :");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button();
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        btn.setText("Connexion >");

        // Gestionnaire d'évenements du boutton

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                String username = userTextField.getText();
                controller.createClient(username);
            }
        });

        // Association des panes

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(40));
        root.getChildren().addAll(sceneTitle, sceneSubtitle, grid);

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
