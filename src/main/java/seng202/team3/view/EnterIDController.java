package seng202.team3.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for fxml file enter-id.fxml
 * @author mattgarrett
 */
public class EnterIDController implements Initializable {

    @FXML TextField crimeIDInput;

    private ReportCrimeWindow activeReportSession;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportCrimeWindow.setIDController(this);
    }

    /**
     * Handles the ID being entered by the user and button to confirm clicked.
     */
    @FXML
    public void confirmID() {
        activeReportSession.setInputID (crimeIDInput.getText());
        ReportCrimeWindow.closeIDStage();
    }

    public void setActiveReportSession (ReportCrimeWindow session) {
        activeReportSession = session;
    }

}
