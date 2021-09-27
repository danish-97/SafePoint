package seng202.team3.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Constructs CrimeData objects to DataPane objects to be displayed
 */
public class DataPaneConstructor {

    /**
     * @return the pane containing all the CrimePanes
     */
    public static VBox loadActiveCrimes() {
        VBox root = new VBox();
        ArrayList<CrimeData> activeData = UIDataInterface.getActiveData();
        ArrayList<Pane> panes = new ArrayList<>();
        for (CrimeData data : activeData) {
            if (data instanceof PoliceData) {
                panes.add(new PoliceDataPane(data));
            } else if  (data instanceof UserData) {
                panes.add (new UserDataPane(data));
            }
        }
        root.getChildren().addAll(panes);
        return root;
    }

}
