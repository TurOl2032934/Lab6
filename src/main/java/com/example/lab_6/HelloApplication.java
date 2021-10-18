package com.example.lab_6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setMaximized(true);

        BorderPane borderPane = new BorderPane();

        //Menu en haut
        MenuBar menuBar = new MenuBar();
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");
        Menu charger = new Menu("Charger une image");
        MenuItem image1 = new MenuItem("Image #1");
        MenuItem image2 = new MenuItem("Image #2");
        MenuItem image3 = new MenuItem("Image #3");
        MenuItem reinisialiser = new MenuItem("Réinisialiser");

        menuBar.getMenus().addAll(fichiers, actions);
        fichiers.getItems().addAll(charger);
        actions.getItems().addAll(reinisialiser);
        charger.getItems().addAll(image1, image2, image3);

        ContextMenu contextMenu = new ContextMenu(fichiers, actions);
        borderPane.setOnContextMenuRequested((n) -> contextMenu.show(borderPane.getCenter(), n.getSceneX(), n.getSceneY()));

        //Information en bas
        Label information = new Label("");

        //Textes
        Label luminositeText = new Label("Luminosité");
        Label contrasteText = new Label("Contraste");
        Label teinteText = new Label("Teinte");
        Label saturationText = new Label("Saturation");

        //Sliders
        Slider luminosite = new Slider(-1, 1, 0);
        luminosite.setShowTickLabels(true);
        luminosite.setShowTickMarks(true);
        luminosite.setMajorTickUnit(1);
        luminosite.setMinorTickCount(1);
        Tooltip aideluminosite = new Tooltip("Clair ou sombre");
        luminosite.setTooltip(aideluminosite);

        Slider contraste = new Slider(-1, 1, 0);
        contraste.setShowTickLabels(true);
        contraste.setShowTickMarks(true);
        contraste.setMajorTickUnit(1);
        contraste.setMinorTickCount(1);
        Tooltip aidecontraste = new Tooltip("Variation ombre et lumière");
        contraste.setTooltip(aidecontraste);

        Slider teinte = new Slider(-1, 1, 0);
        teinte.setShowTickLabels(true);
        teinte.setShowTickMarks(true);
        teinte.setMajorTickUnit(1);
        teinte.setMinorTickCount(1);
        Tooltip aideteinte = new Tooltip("Modification nuances");
        teinte.setTooltip(aideteinte);

        Slider saturation = new Slider(-1, 1, 0);
        saturation.setShowTickLabels(true);
        saturation.setShowTickMarks(true);
        saturation.setMajorTickUnit(1);
        saturation.setMinorTickCount(1);
        Tooltip aidesaturation = new Tooltip("Remplir les couleurs");
        saturation.setTooltip(aidesaturation);

        VBox modification = new VBox(luminositeText, luminosite, contrasteText, contraste, teinteText, teinte, saturationText, saturation);
        modification.setAlignment(Pos.CENTER);

        ImageView imagedefault = new ImageView("file:default.png");
        imagedefault.setFitWidth(500);
        imagedefault.setPreserveRatio(true);

        HBox interfaceMenu = new HBox(imagedefault, modification);
        interfaceMenu.setSpacing(100);
        interfaceMenu.setAlignment(Pos.CENTER);

        //Charger les images
        image1.setOnAction((n) -> imagedefault.setImage(new Image("file:image1.png")) );
        image2.setOnAction((n) -> imagedefault.setImage(new Image("file:image2.png")) );
        image3.setOnAction((n) -> imagedefault.setImage(new Image("file:image3.png")) );

        //Réinisialiser
        reinisialiser.setOnAction((n) -> {
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            information.setText("Les modifications ont été réinisialisées");
        });

        //Ajustement couleurs
        ColorAdjust imageColors = new ColorAdjust();
        imagedefault.setEffect(imageColors);

        luminosite.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setBrightness((double) newValue));
        contraste.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setContrast((double) newValue));
        teinte.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setHue((double) newValue));
        saturation.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setSaturation((double) newValue));

        //BorderPane
        borderPane.setTop(menuBar);
        borderPane.setCenter(interfaceMenu);
        borderPane.setBottom(information);

        stage.setScene(new Scene(borderPane));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}