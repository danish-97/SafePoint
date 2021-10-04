package seng202.team3.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Handles the window for when a user wants to report a crime
 */
public class ReportCrimeWindow {

    //TODO create parent class for all windows

    private static Stage stage = new Stage();

    /**
     * Constructs and opens up a new window to report crimes
     */
    public ReportCrimeWindow() {
        initReportCrimeWindow ();
    }

    /**
     * Opens up the Report crime window
     */
    public void initReportCrimeWindow () {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/report-crime-view.fxml")));
            stage.setTitle("Report Crime");
            stage.setScene(new Scene(root, 250, 335));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the window when the user inputs correct data and wants to submit the report. This just closes the window
     * but doesn't delete it.
     */
    public static void closeStage() {
        stage.close();
    }

}
