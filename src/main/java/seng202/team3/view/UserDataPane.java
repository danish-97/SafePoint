package seng202.team3.view;

import javafx.scene.control.Label;
import seng202.team3.model.CrimeData;

/**
 * UserDataPane extends DataPane that displays the data from a UserData object in a graphical representation.
 * This is used on the CrimeData scroll pane on the main GUI
 * @author Matthew Garrett
 */
public class UserDataPane extends DataPane {

    /**
     * Constructs a new UserDataPane
     * @param data Data to be displayed on the pane
     */
    public UserDataPane(CrimeData data) {
        super(data);
        constructUserComponents();
    }

    /**
     * Constructs UserData unique attributes for DataPane and adds them to the pane.
     */
    public void constructUserComponents() {
        Label arrestMade = new Label("Arrest Made: NO");
        arrestMade.setStyle("-fx-translate-x: 5; -fx-translate-y: 28;");

        this.getChildren().addAll(arrestMade);
    }
}
