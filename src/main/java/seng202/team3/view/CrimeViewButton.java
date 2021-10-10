package seng202.team3.view;

import javafx.scene.control.Button;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

import java.text.ParseException;

/**
 * CrimeViewButton is the button on DataPane for viewing a crime in more detail.
 * @author mattgarrett
 */
public class CrimeViewButton extends Button {

    public CrimeViewButton (CrimeData data) {
        super("View"); //create regular button
        this.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-font-size: 10; -fx-padding: 0; -fx-pref-width: 50; -fx-translate-x: 65; -fx-translate-y: 60;");
        setActions (data);
    }

    /**
     * Handles what happens when the button is clicked. If the compare crime checkbox is not selected then
     * it opens a new window to view the crime, else it opens a compare crime window with the two
     * crimes to be compared.
     * @param data CrimeData object that this button is related to
     */
    public void setActions (CrimeData data) {
        this.setOnAction(value -> {
            if (UIDataInterface.isComparingCrimes()) {
                //if this is the second crime to be selected
                if (UIDataInterface.getComparator() != null && UIDataInterface.getComparator() != data) {
                    try {
                        new CompareDataWindow(UIDataInterface.getComparator(), data);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    UIDataInterface.setComparator(null); //resets comparing data
                } else {
                    UIDataInterface.setComparator(data);
                }
            } else {
                //opens up a regular view data window.
                if (data instanceof PoliceData) {
                    new PoliceDataWindow((PoliceData) data);
                } else if (data instanceof UserData) {
                    new UserDataWindow((UserData) data);
                }
            }
        });
    }

}
