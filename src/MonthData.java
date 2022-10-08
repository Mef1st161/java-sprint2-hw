import java.util.ArrayList;
import java.util.Arrays;

public class MonthData {
    private String monthFileName;
    private String monthName;
    private String yearName;
    private String actualMonthName;
    private String[] headers;
    private ArrayList<MonthEntry> monthEntries = new ArrayList<>();
    private int expense;
    private int income;
    private int maxExpenseValue = 0;
    private String maxExpenseName = "";
    private int maxIncomeValue = 0;
    private String maxIncomeName = "";

    public MonthData(String monthFileName, String[] headers, ArrayList<MonthEntry> monthEntries){
        setMonthFileName(monthFileName);
        setHeaders(headers);
        setMonthEntries(monthEntries);
        calculateExpenseAndIncome();
        parseName(monthFileName);
        defineActualMonthName(monthName);

    }

    public void findMaxExpenseAndIncome(){
        for (MonthEntry monthEntry : monthEntries){
            if (maxExpenseValue < monthEntry.getLocalExpense()){
                this.maxExpenseValue = monthEntry.getLocalExpense();
                this.maxExpenseName = monthEntry.getItemName();
            }
            if (maxIncomeValue < monthEntry.getLocalIncome()){
                this.maxIncomeValue = monthEntry.getLocalIncome();
                this.maxIncomeName = monthEntry.getItemName();
            }

        }
    }
    public void showMaxExpenseAndIncome(){
        if (maxExpenseValue == 0 && maxIncomeValue == 0){
            findMaxExpenseAndIncome();
        }
        System.out.println("\t\tСамая большая трата месяца: " + FinalVariables.ANSI_RED +this.maxExpenseName + FinalVariables.ANSI_RESET +" и составило " + this.maxExpenseValue);
        System.out.println("\t\tСамый прибыльный товар месяца: " + FinalVariables.ANSI_GREEN +this.maxIncomeName + FinalVariables.ANSI_RESET + " и составило " + this.maxIncomeValue);
    }
    public void calculateExpenseAndIncome(){
        for (MonthEntry monthEntry: monthEntries){
            if (monthEntry.getExpense()){
                this.expense += monthEntry.getQuantity() * monthEntry.getSumOfOne();
            }
            else{
                this.income += monthEntry.getQuantity() * monthEntry.getSumOfOne();
            }
        }
    }

    private void parseName(String monthFileName){
        String monthName = "";
        String yearName = "";
        yearName = monthFileName.substring(2,6);
        monthName = monthFileName.substring(6,8);
        setMonthName(monthName);
        setYearName(yearName);

    }

    public String getMonthFileName() {
        return monthFileName;
    }

    public void setMonthFileName(String monthFileName) {
        this.monthFileName = monthFileName;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public ArrayList<MonthEntry> getMonthEntries() {
        return monthEntries;
    }

    public void setMonthEntries(ArrayList<MonthEntry> monthEntries) {
        this.monthEntries = monthEntries;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getActualMonthName() {
        return actualMonthName;
    }

    public void setActualMonthName(String actualMonthName) {
        this.actualMonthName = actualMonthName;
    }

    public int getMaxExpenseValue() {
        return maxExpenseValue;
    }

    public void setMaxExpenseValue(int maxExpenseValue) {
        this.maxExpenseValue = maxExpenseValue;
    }

    public String getMaxExpenseName() {
        return maxExpenseName;
    }

    public void setMaxExpenseName(String maxExpenseName) {
        this.maxExpenseName = maxExpenseName;
    }

    public int getMaxIncomeValue() {
        return maxIncomeValue;
    }

    public void setMaxIncomeValue(int maxIncomeValue) {
        this.maxIncomeValue = maxIncomeValue;
    }

    public String getMaxIncomeName() {
        return maxIncomeName;
    }

    public void setMaxIncomeName(String maxIncomeName) {
        this.maxIncomeName = maxIncomeName;
    }

    private void defineActualMonthName(String monthName){
        int monthNumber = Integer.parseInt(monthName);
        switch (monthNumber){
            case (1): setActualMonthName("Январь");
                break;
            case (2): setActualMonthName("Февраль");
                break;
            case (3): setActualMonthName("Март");
                break;
            case (4): setActualMonthName("Апрель");
                break;
            case (5): setActualMonthName("Май");
                break;
            case (6): setActualMonthName("Июнь");
                break;
            case (7): setActualMonthName("Июль");
                break;
            case (8): setActualMonthName("Август");
                break;
            case (9): setActualMonthName("Сентябрь");
                break;
            case (10): setActualMonthName("Октябрь");
                break;
            case (11): setActualMonthName("Ноябрь");
                break;
            case (12): setActualMonthName("Декабрь");
                break;
        }
    }

    @Override
    public String toString() {
        return "MonthData{" +
                "monthName='" + monthFileName + '\'' +
                ", headers=" + Arrays.toString(headers) +
                ", monthEntries=" + monthEntries +
                '}';
    }
}
