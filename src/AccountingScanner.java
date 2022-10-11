import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class AccountingScanner {
    private static final File dir = new File("resources");

    public MonthData scanMonthlyReport(File file) {
        ArrayList<MonthEntry> monthEntries = new ArrayList<>();
        String monthDataString = readFileContentsOrNull(file.getPath());
        if (monthDataString != null) {
           String[] line = monthDataString.split("\\n");
           String[] headers = line[0].split(",");
            for (int i = 1; i < line.length; i ++) {
                String[]  splitLine = line[i].split(",");
                MonthEntry monthEntry = new MonthEntry(splitLine[0], getBool(splitLine[1]),
                        Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
                monthEntries.add(monthEntry);
            }
            return new MonthData(file.getName(), headers, monthEntries);
        }
        return null;
    }

    public YearData scanYearlyReport(File file) {
        ArrayList<YearEntry> yearEntries = new ArrayList<>();
        String yearDataString = readFileContentsOrNull(file.getPath());
        if (yearDataString != null) {
            String[] line = yearDataString.split("\\n");
            String[] headers = line[0].split(",");
            for (int i = 1; i < line.length; i++){
                String[] splitLine = line[i].split(",");
                YearEntry yearEntry = new YearEntry(splitLine[0], Integer.parseInt(splitLine[1]),
                        getBool(splitLine[2]));
                yearEntries.add(yearEntry);
            }
            return new YearData(file.getName(),headers, yearEntries);
        }
        return null;
    }

    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом." +
                    " Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public  ArrayList<MonthData> readAllMonthFiles() {
        ArrayList<MonthData> monthDataArrayList = new ArrayList<>();
        File[] files = dir.listFiles();
        ArrayList<File> monthFiles = new ArrayList<>();
        if (files != null) {
            for (File file: files) {
                if (file.getName().contains("m")) {
                    monthFiles.add(file);
                }
            }
            for (File file: monthFiles) {
                monthDataArrayList.add(scanMonthlyReport(file));
            }
        }
        return monthDataArrayList;
    }

    public  ArrayList<YearData> readAllYearFiles() {
        ArrayList<YearData> yearDataArrayList = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file: files) {
                if (file.getName().contains("y")) {
                    yearDataArrayList.add(scanYearlyReport(file));
                }
            }
        }
        return yearDataArrayList;
    }

    private boolean getBool(String value){
        return (value.equalsIgnoreCase("true"));
    }
}
