package seng202.team3.controller;

import seng202.team3.model.CrimeData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;


public class GraphCreator {

    public Map<Integer, List<CrimeData>> formattedDatesIntoGroups(ArrayList<CrimeData> crimeData) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);

        Map<Integer, List<CrimeData>> weeklyCrimes = new HashMap<>();
        for (CrimeData crime: crimeData) {
            ArrayList<CrimeData> crimesTemp = null;
            Integer weekOfYear = simpleDateFormat.parse(crime.getDate()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().get(weekFields.weekOfWeekBasedYear());
            if (weeklyCrimes.get(weekOfYear) == null) {
                crimesTemp = new ArrayList<>();
            } else {
                crimesTemp = (ArrayList<CrimeData>) weeklyCrimes.get(weekOfYear);
            }
            crimesTemp.add(crime);
            weeklyCrimes.put(weekOfYear, crimesTemp);
        }

        return weeklyCrimes;
    }

    public static void main(String[] args) throws ParseException {
        GraphCreator creator = new GraphCreator();
        ReadCSV readCSV = new ReadCSV();
        ArrayList<CrimeData> crimeData = readCSV.readDataLineByLine("data.csv");
        creator.formattedDatesIntoGroups(crimeData);
    }
}
