package seng202.team3.model;

public enum CrimeStat {
    LOCATION,
    CRIME_TYPE,
    ARREST_MADE,
    DATE,
    DATE_RANGE,
    POLICE_DATA,
    USER_DATA,
    HIGH_FREQUENCY,
    LOW_FREQUENCY,
    HIGH_RISK_AREA,
    LOW_RISK_AREA
}

// Note that HIGH_FREQ, LOW_FREQ, HIGH_RISK and LOW_RISK will not be filtered, but SORTED
// All other enum values are used as filtering values