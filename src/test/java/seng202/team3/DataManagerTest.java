package test.java.seng202.team3;

public class DataManagerTest {
    public void getDataByIDTest() {
        Object data = new DataManager();
        CrimeData crimeData = new CrimeData("JE123990", "27/08/2021", "1 RANDOM STREET", "THEFT" );
        CrimeData crimeTest = data.getDataByID("JE123990");
        assertEquals(crimeData, crimeTest);
    }
}
