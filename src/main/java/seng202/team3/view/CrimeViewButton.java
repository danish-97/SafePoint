package seng202.team3.view;

import javafx.scene.control.Button;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.CrimeData;
import seng202.team3.model.PoliceData;
import seng202.team3.model.UserData;

public class CrimeViewButton extends Button {

    public CrimeViewButton (CrimeData data) {
        super("View");
        this.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-font-size: 10; -fx-padding: 0; -fx-pref-width: 50; -fx-translate-x: 65; -fx-translate-y: 60;");
        setActions (data);
    }

    public void setActions (CrimeData data) {
        this.setOnAction(value -> {
            if (UIDataInterface.isComparingCrimes()) {
                if (UIDataInterface.getComparator() != null) {
                    new CompareDataWindow(UIDataInterface.getComparator(), data);
                    UIDataInterface.setComparator(null);
                } else {
                    UIDataInterface.setComparator(data);
                }
            } else {
                if (data instanceof PoliceData) {
                    new PoliceDataWindow((PoliceData) data);
                } else if (data instanceof UserData) {
                    new UserDataWindow((UserData) data);
                }
            }
        });
    }

}
