package seng202.team3.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.team3.model.CrimeData;

import java.io.IOException;
import java.util.Objects;

/**
 * Handles the window for when a user wants to report a crime
 * @author mattgarrett
 */
public class ReportCrimeWindow {

    //TODO create parent class for all windows

    private static Stage stage = new Stage();
    private static Stage IDStage = new Stage();


    private static ReportCrimeController activeController;
    private static EnterIDController idController;

    private CrimeData activeData;

    /**
     * Constructs and opens up a new window to report crimes
     */
    public ReportCrimeWindow() {
        initReportCrimeWindow ();
    }

    public ReportCrimeWindow(CrimeData data) {
        activeData = data;
        confirmUserID();
    }

    public void setInputID (String input) {
        String formattedID = Integer.toString(Integer.parseInt(activeData.getId()) * 7159);
        if (Objects.equals(input, formattedID)) {
            initReportCrimeWindow();
            setAttributes(activeData);
        }
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


    public void setAttributes (CrimeData data) {
       activeController.setAttributes(data);
    }

    public void confirmUserID () {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/enter-id.fxml")));
            IDStage.setTitle("Confirm ID");
            IDStage.setScene(new Scene(root, 269, 119));
            IDStage.setResizable(false);
            IDStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        idController.setActiveReportSession(this);
    }

    /**
     * Closes the window when the user inputs correct data and wants to submit the report. This just closes the window
     * but doesn't delete it.
     */
    public static void closeStage() {
        stage.close();
    }

    public static void closeIDStage() {IDStage.close();}

    public static void setActiveController (ReportCrimeController controller) {
        activeController = controller;
    }

    public static void setIDController (EnterIDController controller) {idController = controller;}

}
