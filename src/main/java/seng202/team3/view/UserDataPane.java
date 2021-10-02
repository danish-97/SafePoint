package seng202.team3.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import seng202.team3.model.CrimeData;

import java.util.Objects;

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

        ImageView settingsImage = new ImageView(Objects.requireNonNull(getClass().getClassLoader().getResource("seng202.team3.view/settings.png")).toExternalForm());

        Button settingsButton = new Button();
        settingsButton.setStyle("-fx-translate-x: 135; -fx-translate-y: 2; -fx-background-color: transparent; -fx-border-color: transparent;");
        settingsButton.setPrefSize(16, 16);
        settingsButton.setGraphic(settingsImage);

        this.getChildren().addAll(arrestMade, settingsButton);
    }
}
