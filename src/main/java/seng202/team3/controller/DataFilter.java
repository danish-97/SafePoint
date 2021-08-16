package seng202.team3.controller;

import seng202.team3.model.CrimeStat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataFilter {

    private ArrayList<CrimeStat> activeFilters = new ArrayList<CrimeStat>();


    public ArrayList<CrimeData> filterData(ArrayList<CrimeData> data) {
        ArrayList<CrimeData> filteredData = new ArrayList<CrimeData>();
        for (CrimeStat filter : activeFilters) {
            filteredData.addAll(filterCrimeData(filter, data));
        }
        return filteredData;

    }

    public ArrayList<CrimeData> filterCrimeData(CrimeStat filter, ArrayList<CrimeData> data) {
        ArrayList<CrimeData> singleFilterArray = new ArrayList<CrimeData>();
        // TODO Finish implementing filterCrimeData
        return singleFilterArray;
    }

    public void setActiveFilters(ArrayList<CrimeStat> activeFilters) {
        this.activeFilters = activeFilters;
    }
}
