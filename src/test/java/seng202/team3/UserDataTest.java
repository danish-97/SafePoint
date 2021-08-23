package seng202.team3;

import org.junit.Test;
import seng202.team3.controller.UserData;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserDataTest {
    @Test
    public void checkDate() {
        UserData newDate = new UserData();
        newDate.setDay(15);
        newDate.setMonth(5);
        newDate.setYear(2020);
        assertEquals(15/5/2020, newDate.dateReported());
    }
}
