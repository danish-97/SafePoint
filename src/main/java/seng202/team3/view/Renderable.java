package seng202.team3.view;

import javafx.scene.layout.Pane;

public interface Renderable {
    void createPane() throws ClassNotFoundException;
    Pane getPane();
}
