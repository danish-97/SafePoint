package seng202.team3.view;

import javafx.scene.control.Label;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;

/**
 * PoliceDataPane extends DataPane that displays the data from a PoliceData object in a graphical representation.
 * This is used on the CrimeData scroll pane on the main GUI
 * @author Matthew Garrett
 */
public class PoliceDataPane extends DataPane{

    /**
     * Constructs a new PoliceDataPane
     * @param data Data to be displayed on the pane
     */
    public PoliceDataPane (CrimeData data) {
        super(data);
        constructPoliceComponents ((PoliceData) data);
    }

    /**
     * Constructs PoliceData unique attributes for DataPane and adds them to the pane.
     */
    public void constructPoliceComponents (PoliceData data) {
        Label arrestMade = new Label("Arrest Made: " + data.isArrestMade());
        arrestMade.setStyle("-fx-translate-x: 5; -fx-translate-y: 28;");

        this.getChildren().addAll(arrestMade);
    }

}
