package com.mycompany.projectdesktop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public final class Section extends VBox {    
    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView imageView;
    
    public Section(String title, String description, Image image) {
        try {
            loadFXML();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setTitle(title);
        setDescription(description);
        setImage(image);
    }
    
    public static Section random() {
        String country = Fetch.randomCountry();
        String bacon = Fetch.bacon();
        Image image = Fetch.randomImage(country);
        Section section = new Section(country, bacon, image);
        
        return section;
    }
    
    public void setTitle(String title) {
        titleLabel.setText(title);
    }
    
    public void setDescription(String description) {
        descriptionLabel.setText(description);
    }
    
    public void setImage(Image image) {
        this.imageView.setImage(image);
    }
    
    // Carrega o FXML da Section. Fazer dessa forma facilita já que dá pra usar
    // o Scene Builder pra modelar o componente. Até daria pra fazer o componente
    // sem usar FXML, mas ai teria que setar tudo manualmente em java
    private void loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Section.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
}