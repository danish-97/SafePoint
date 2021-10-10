import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeAll;
import seng202.team3.controller.FilterController;
import seng202.team3.controller.UIDataInterface;
import seng202.team3.model.*;

import java.util.ArrayList;
import static org.junit.Assert.*;

// Cucumber tests will pass if data.csv file is utilised.

public class StepDefinitions {

    private ArrayList activeCrimeData;
    private DataFilter dataFilter = new DataFilter();
    private ArrayList<CrimeData> filteredCrimes;

    @BeforeAll
    public void clearFilters() {
        FilterController.setActiveLocation(null);
        FilterController.setActiveCrimeType(null);
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(true);
        FilterController.setArrestMade(false);
        FilterController.setDateFilteringActive(false);
        FilterController.setRegionDataActive(false);
        FilterController.setRegionFilteringKey(null);
    }

    @Given("crimes have been loaded to sidebar and no filters have been selected")
    public void crimes_have_been_loaded() {
        clearFilters();
        UIDataInterface.initCrimeData();
        activeCrimeData = DataManager.getData();
    }


    @When("I choose to display police data")
    public void filter_police_data() {
        FilterController.setPoliceDataActive(true);
        FilterController.setUserDataActive(false);
        filteredCrimes = dataFilter.filterData(activeCrimeData);
    }

    @When("I choose to display user data")
    public void filter_user_data() {
        FilterController.setUserDataActive(true);
        FilterController.setPoliceDataActive(false);
        filteredCrimes = dataFilter.filterData(activeCrimeData);
    }

    @When("I choose the type of crime \\(theft)")
    public void choose_type_theft() {
        FilterController.setActiveCrimeType("THEFT");
        filteredCrimes = dataFilter.filterData(activeCrimeData);
    }



    @And("I reload the data")
    public void reload_data() {
        // Add method for reloading data
    }

    @When("I choose to filter by arrest made")
    public void filter_by_arrest_made() {
        FilterController.setArrestMade(true);
        filteredCrimes = dataFilter.filterData(activeCrimeData);
    }

    @Then("I should only see crimes where arrests were made")
    public void i_see_arrest_made_crimes() {
        assertEquals(((PoliceData)filteredCrimes.get(0)).isArrestMade(), "YES");
    }

    @Then("I should not see any crimes")
    public void see_no_crimes() {
        assertEquals(0, filteredCrimes.size());
    }
    
    @Then("I should only see police data")
    public void see_only_police_data() {
        assertEquals(true, filteredCrimes.get(0) instanceof PoliceData);
    }
    
    @Then("I should only see user data")
    public void see_only_user_data() {
        assertEquals(true, filteredCrimes.get(0) instanceof UserData);
        
    }

    @Then("I should only see crimes of that type \\(theft) where arrests were made")
    public void iShouldOnlySeeCrimesOfThatTypeWhereArrestsWereMade() {
        assertEquals("THEFT", filteredCrimes.get(0).getCrimeType());
        assertEquals("YES", ((PoliceData)filteredCrimes.get(0)).isArrestMade());
        
    }

    @Then("I should only see the chosen type of crime \\(theft)")
    public void iShouldOnlySeeTheChosenTypeOfCrimeTheft() {
        assertEquals("THEFT", filteredCrimes.get(0).getCrimeType());
    }

    @When("I choose to sort by high frequency")
    public void iChooseToSortByHighFrequency() {
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("HIGH FREQUENCY");
        filteredCrimes = dataFilter.filterData(activeCrimeData);
    }

    @When("I choose to sort by low frequency")
    public void iChooseToSortByLowFrequency() {
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("LOW FREQUENCY");
        filteredCrimes = dataFilter.filterData(activeCrimeData);
    }


    @When("I choose to sort by high risk areas")
    public void iChooseToSortByHighRiskAreas() {
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("HIGH RISK AREAS");
        filteredCrimes = dataFilter.filterData(activeCrimeData);

    }


    @When("I choose to sort by low risk areas")
    public void iChooseToSortByLowRiskAreas() {
        FilterController.setRegionDataActive(true);
        FilterController.setRegionFilteringKey("LOW RISK AREAS");
        filteredCrimes = dataFilter.filterData(activeCrimeData);

    }


    @Then("the crimes on the sidebar will be sorted based on high frequency")
    public void theCrimesOnTheSidebarWillBeSortedBasedOnHighFrequency() {
        // Most frequent crime in the file is theft
        assertEquals("THEFT", filteredCrimes.get(0).getCrimeType());
    }


    @Then("the crimes on the sidebar will be sorted based on low frequency")
    public void theCrimesOnTheSidebarWillBeSortedBasedOnLowFrequency() {
        // Least frequent crime in the file is concealed carry licence, an user reported crime
       assertEquals("OTHER NARCOTIC VIOLATION", filteredCrimes.get(0).getCrimeType());
    }


    @Then("the crimes on the sidebar will be sorted based on high risk areas")
    public void theCrimesOnTheSidebarWillBeSortedBasedOnHighRiskAreas() {
        assertEquals("28", ((PoliceData)filteredCrimes.get(0)).getWard());
    }

    @Then("the crimes on the sidebar will be sorted based on low risk areas")
    public void theCrimesOnTheSidebarWillBeSortedBasedOnLowRiskAreas() {
        assertEquals("23", ((PoliceData)filteredCrimes.get(0)).getWard());
    }
}


