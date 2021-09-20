package seng202.team3.view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.text.Position;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private WebView mapView;

    private WebEngine webEngine;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMap();

    }

    private void initMap() {
        webEngine = mapView.getEngine();
        webEngine.load(getClass().getClassLoader().getResource("seng202.team3.view/googleMap.html").toExternalForm());
    }

}

