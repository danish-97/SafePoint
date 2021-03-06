package seng202.team3.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Class which creates the Window that pops up after clicking the View Graph Button.
 * @author Danish Jahangir
 */
public class GraphViewWindow {

    private static Stage stage = new Stage();

    /**
     * Constructor method for the class GraphViewWindow
     */
    public GraphViewWindow() {
        initGraphViewWindow();
    }

    private void initGraphViewWindow() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/graphView.fxml")));
            stage.setTitle("Graph View");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
