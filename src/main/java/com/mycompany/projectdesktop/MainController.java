package com.mycompany.projectdesktop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainController {    
    @FXML
    private ImageView loadingGif;
    
    @FXML 
    private VBox sectionsContainer;

    @FXML
    private void initialize() {        
        String title = "Seção Importante";
        String description = "Esta é uma seção com conteúdo relevante.";
        Image logo = new Image("com/mycompany/projectdesktop/assets/logo.jpg");
        Section section = new Section(title, description, logo);
        
        sectionsContainer.getChildren().add(section);
    }

    @FXML
    private void onButtonClick() {
        // Cria a Section em um thread separado para não interromper a UI
        new Thread(() -> {
            loadingGif.setVisible(true);
            Section section = Section.random();
            loadingGif.setVisible(false);

            Platform.runLater(() -> {
                // Esse trecho precisa ser executado no thread principal
                sectionsContainer.getChildren().add(0, section);
            });
        }).start();
    }
}
