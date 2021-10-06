package seng202.team3.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EnterIDController implements Initializable {

    @FXML TextField crimeIDInput;

    private ReportCrimeWindow activeReportSession;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportCrimeWindow.setIDController(this);
    }

    @FXML
    public void confirmID (ActionEvent e) {
        activeReportSession.setInputID (crimeIDInput.getText());
        ReportCrimeWindow.closeStage();
    }

    public void setActiveReportSession (ReportCrimeWindow session) {
        activeReportSession = session;
    }

}
