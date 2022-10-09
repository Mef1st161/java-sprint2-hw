import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingScanner {
    public Scanner scanner;
    private static final File dir = new File("java-sprint2-hw/resources");

    public MonthData scanMonthlyReport(File path){
        try {
            scanner = new Scanner(path);
            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(",");

            String line;
            String[] splitLine;
            ArrayList<MonthEntry> monthEntries = new ArrayList<>();
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                splitLine = line.split(",");
                MonthEntry monthEntry = new MonthEntry(splitLine[0], getBool(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
                monthEntries.add(monthEntry);
            }
            return new MonthData(path.getName(), headers, monthEntries);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public YearData scanYearlyReport(File path){
        try {
            scanner = new Scanner(path);
            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(",");
            String line;
            String[] splitLine;
            ArrayList<YearEntry> yearEntries = new ArrayList<>();
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                splitLine = line.split(",");
                YearEntry yearEntry = new YearEntry(splitLine[0], Integer.parseInt(splitLine[1]), getBool(splitLine[2]));
                yearEntries.add(yearEntry);
            }
            return new YearData(path.getName(),headers, yearEntries);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public  ArrayList<MonthData> readAllMonthFiles(){
        ArrayList<MonthData> monthDataArrayList = new ArrayList<>();
        File[] files = dir.listFiles();
        ArrayList<File> monthFiles = new ArrayList<>();
        if (files != null) {
            for (File file: files){
                if (file.getName().contains("m")){
                    monthFiles.add(file);
                }
            }
            for (File file: monthFiles){
                monthDataArrayList.add(scanMonthlyReport(file));
            }
        }
        return monthDataArrayList;
    }

    public  ArrayList<YearData> readAllYearFiles(){
        ArrayList<YearData> yearDataArrayList = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null){
            for (File file: files){
                if (file.getName().contains("y")){
                    yearDataArrayList.add(scanYearlyReport(file));
                }
            }
        }
        return yearDataArrayList;
    }

    private boolean getBool(String value){
        return (value.toLowerCase().equals("true"));
    }
}
