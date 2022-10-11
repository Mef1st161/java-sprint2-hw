import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<MonthData> monthDataArrayList = new ArrayList<>();
    private static ArrayList<YearData> yearDataArrayList = new ArrayList<>();
    static AccountingScanner accountingScanner = new AccountingScanner();
    public static Scanner scanner = new Scanner(System.in);
    static FinanceHandler financeHandler;

    public static void main(String[] args) {
        int userInput = FinalColorAndMenuVariables.USER_INPUT_INITIAL_POINT;
        while (userInput != FinalColorAndMenuVariables.USER_INPUT_FINISH_POINT){
            userInput = showMenu(userInput);
            menuSwitch(userInput);
        }
    }

    public static int showMenu(int userInput) {
            if (userInput == FinalColorAndMenuVariables.USER_INPUT_EXIT_MENU) {
                System.out.println(FinalColorAndMenuVariables.ANSI_RED + "\t\t Вы уверены? y/n" + FinalColorAndMenuVariables.ANSI_RESET);
                String userAnswer = scanner.next();
                if (userAnswer.equals("n") || userAnswer.equals("N")) {
                    return FinalColorAndMenuVariables.USER_INPUT_INITIAL_POINT;
                }
                else if (userAnswer.equals("y") || userAnswer.equals("Y")) {
                    return FinalColorAndMenuVariables.USER_INPUT_FINISH_POINT;
                }
                else {
                    System.out.println("Повторите ответ: y = да, n = нет");
                    return FinalColorAndMenuVariables.USER_INPUT_EXIT_MENU;
                }
            }
            else {
                System.out.println(FinalColorAndMenuVariables.ANSI_CYAN + "Выберите действие:\n\t" + FinalColorAndMenuVariables.ANSI_RESET +
                        "1 - Считать все месячные отчеты\n\t" +
                        "2 - Считать Годовой отчет\n\t" +
                        "3 - Сверить отчеты\n\t" +
                        "4 - Вывести информацию о всех месячных отчетах\n\t" +
                        "5 - Вывести информацию о годовом отчете\n\t" +
                        "6 - Выход");
                userInput =  scanner.nextInt();
            }

            if (userInput < 0 || userInput > 6) {
                System.out.println(FinalColorAndMenuVariables.ANSI_RED + "Такого пункта в меню нет, повторите" + FinalColorAndMenuVariables.ANSI_RESET);
                return FinalColorAndMenuVariables.USER_INPUT_INITIAL_POINT;
            }

            return userInput;
    }
    public static void menuSwitch(int userInput) {
        switch (userInput) {
            case (FinalColorAndMenuVariables.USER_INPUT_READ_ALL_MONTHS_MENU):
                if (monthDataArrayList.size() == 0) {
                    monthDataArrayList = accountingScanner.readAllMonthFiles();
                }
                System.out.println(FinalColorAndMenuVariables.ANSI_BLUE + "Месячные отчеты считаны, было найдено отчетов: " + monthDataArrayList.size() + FinalColorAndMenuVariables.ANSI_RESET);
                break;
            case (FinalColorAndMenuVariables.USER_INPUT_READ_ALL_YEARS_MENU):
                if (yearDataArrayList .size() == 0) {
                    yearDataArrayList = accountingScanner.readAllYearFiles();
                }
                System.out.println(FinalColorAndMenuVariables.ANSI_BLUE + "Годовые отчеты считаны, было найдено отчетов: " + yearDataArrayList.size() + FinalColorAndMenuVariables.ANSI_RESET);
                break;
            case (FinalColorAndMenuVariables.USER_INPUT_COMPARE_REPORTS_MENU):
                if (!monthDataArrayList.isEmpty() && !yearDataArrayList.isEmpty()) {
                    if (financeHandler == null){
                        financeHandler = new FinanceHandler(yearDataArrayList, monthDataArrayList);
                    }
                    financeHandler.compareYearAndMonth();
                }
                else {
                    System.out.println(FinalColorAndMenuVariables.ANSI_RED + "Сначала считайте месячные и годовые отчеты" + FinalColorAndMenuVariables.ANSI_RESET);
                }
                break;
            case (FinalColorAndMenuVariables.USER_INPUT_SHOW_MONTH_DATA_MENU):
                if (!monthDataArrayList.isEmpty() && !yearDataArrayList.isEmpty()) {
                    if (financeHandler == null){
                        financeHandler = new FinanceHandler(yearDataArrayList, monthDataArrayList);
                    }
                    System.out.println(" ");
                    financeHandler.showMonthExpenses();
                }
                else {
                    System.out.println(FinalColorAndMenuVariables.ANSI_RED + "Сначала считайте месячные и годовые отчеты" + FinalColorAndMenuVariables.ANSI_RESET);
                }
                break;
            case (FinalColorAndMenuVariables.USER_INPUT_SHOW_YEAR_DATA_MENU):
                if (!monthDataArrayList.isEmpty() && !yearDataArrayList.isEmpty()) {
                    if (financeHandler == null){
                        financeHandler = new FinanceHandler(yearDataArrayList, monthDataArrayList);
                    }
                    financeHandler.showYearData();
                }
                else {
                    System.out.println(FinalColorAndMenuVariables.ANSI_RED + "Сначала считайте месячные и годовые отчеты" + FinalColorAndMenuVariables.ANSI_RESET);
                }
                break;
        }
    }
}

