package seng202.team3.controller;

import seng202.team3.model.DisplayState;

import java.util.Date;

public class UIDataInterface {

    private String currCrimeType;
    private String regionActive;
    private String[] activeMapSettings;
    private boolean regionMapping;
    private Date currDate;
    private Date startDate;
    private Date endDate;

    public DisplayState currentState() {
        return null; //TODO Return current state of data
    }

    public String constructUserData() {
        return null; //TODO Return UserData
    }

    public String getCurrCrimeType() {
        return currCrimeType;
    }

    public void setCurrCrimeType(String currCrimeType) {
        this.currCrimeType = currCrimeType;
    }

    public String getRegionActive() {
        return regionActive;
    }

    public void setRegionActive(String regionActive) {
        this.regionActive = regionActive;
    }

    public String[] getActiveMapSettings() {
        return activeMapSettings;
    }

    public void setActiveMapSettings(String[] activeMapSettings) {
        this.activeMapSettings = activeMapSettings;
    }

    public boolean isRegionMapping() {
        return regionMapping;
    }

    public void setRegionMapping(boolean regionMapping) {
        this.regionMapping = regionMapping;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static void updateFilters() {
        //MainViewController mvc = new MainViewController();
        //System.out.println(mvc.getCurrentCrimeType());
    }
}
