package serverAPP;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//////////////////////////////////////////////////////////              MAIN            ///////////////////////////////////////////////

public class GUI extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private Controller controller;

    private VBox conversationContainer;     //pour être accessible depuis controller

    private String clientUsername;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.controller = new Controller(this);

        root = new BorderPane();
        primaryStage.setScene(new Scene(root, 400, 550));
        primaryStage.setTitle("Oscar CHENE - ChatAPP");
        showLoginScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

//////////////////////////////////////////////////////////              LOGIN SCREEN            ///////////////////////////////////////////////    

    private void showLoginScreen() {
        root.getChildren().clear(); //Nettoyage de la racine

        // Conteneur pour composants
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(40));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Composant
            // Titre

        Text sceneTitle = new Text("Bienvenue sur ChatAPP !");
        Text sceneSubtitle = new Text("Entrez votre nom d'utilisateur pour vous connecter au serveur");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

            // Login

        Label userName = new Label("Nom d'utilisateur :");
        
        TextField userTextField = new TextField();
        

        Button btn = new Button();
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        
        btn.setText("Connexion >");

        // Gestionnaire d'évenements du boutton

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                clientUsername = userTextField.getText();
                controller.createClient(clientUsername);
                showConversationScreen();
            }
        });

        // Association des panes
        grid.add(userName, 0, 1);
        grid.add(userTextField, 1, 1);
        grid.add(hbBtn, 1, 4);
        vbox.getChildren().addAll(sceneTitle, sceneSubtitle, grid);

        root.setCenter(vbox);  // ajout des éléments à la scène principale 
    }

//////////////////////////////////////////////////////////              CONVERSATION SCREEN            ///////////////////////////////////////////////    

    private void showConversationScreen() {
        root.getChildren().clear();

        // Créer le titre de la conversation

        Text title = new Text("Conversation de groupe");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox titleHbox = new HBox(5);
        titleHbox.setPadding(new Insets(10));
        titleHbox.getChildren().add(title);
        titleHbox.setAlignment(Pos.CENTER);

        // Créer la zone de conversation
        ScrollPane conversationScrollPane = new ScrollPane();
        conversationContainer = new VBox(); // Pour empiler les messages

        conversationScrollPane.setContent(conversationContainer);
        conversationScrollPane.setFitToWidth(true);
        conversationScrollPane.setFitToHeight(true);
        conversationContainer.setFillWidth(true);

        // Créer la zone de saisie de texte
        TextField messageTextArea = new TextField();
        messageTextArea.setPromptText("Ecrivez un message...");
        
        // Créer le bouton d'envoi
        Button sendButton = new Button("Envoyer");
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                HBox messageBox = new HBox(5);
                messageBox.setPadding(new Insets(5, 0, 5, 5));      // haut droite bas gauche
                messageBox.setAlignment(Pos.CENTER_LEFT);
                Text senderText = new Text("moi : ");
                senderText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                Text msgText = new Text(messageTextArea.getText());
                msgText.setFont(Font.font("Arial", 14));
                messageBox.getChildren().addAll(senderText, msgText);
                conversationContainer.getChildren().add(messageBox);

                controller.sendMessage(messageTextArea.getText());
                messageTextArea.clear();
            }
        });
                

        // Ajouter la zone de saisie de texte et le bouton dans un conteneur horizontal
        HBox inputContainer = new HBox(5);
        HBox.setHgrow(messageTextArea, Priority.ALWAYS);
        inputContainer.getChildren().addAll(messageTextArea, sendButton);
        inputContainer.setPadding(new Insets(10));
        inputContainer.setAlignment(Pos.CENTER);

        // Positionner la zone de conversation en haut et la zone de saisie en bas
        root.setTop(titleHbox);
        root.setCenter(conversationScrollPane);
        root.setBottom(inputContainer);


    }

    public BorderPane getRoot() {
        return root;
    }

    public VBox getConversationContainer() {
        return conversationContainer;
    }
}

