import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<MonthData> monthDataArrayList = new ArrayList<>();
    private static ArrayList<YearData> yearDataArrayList = new ArrayList<>();

    static AccountingScanner accountingScanner = new AccountingScanner();
    public static Scanner scanner = new Scanner(System.in);
    static FinanceHandler financeHandler;


    public static void main(String[] args) throws IOException {
       //ArrayList<MonthData> monthDataArrayList = accountingScanner.readAllMonthFiles();
       //ArrayList<YearData> yearDataArrayList = accountingScanner.readAllYearFiles();


        int userInput = 0;
        while (userInput !=7){
            userInput = showMenu(userInput);
            menuSwitch(userInput);

        }

































        // Поехали!
    }

    public static int showMenu(int userInput){
            if (userInput == 6){
                System.out.println(FinalVariables.ANSI_RED + "\t\t Вы уверены? y/n" + FinalVariables.ANSI_RESET);
                String userAnswer = scanner.next();
                if (userAnswer.equals("n") || userAnswer.equals("N")){
                    return 0;
                }
                else if (userAnswer.equals("y") || userAnswer.equals("Y")){
                    return 7;
                }
                else {
                    System.out.println("Повторите ответ: y = да, n = нет");
                    return 6;
                }
            }
            else {
                System.out.println(FinalVariables.ANSI_CYAN + "Выберите действие:\n\t" + FinalVariables.ANSI_RESET +
                        "1 - Считать все месячные отчеты\n\t" +
                        "2 - Считать Годовой отчет\n\t" +
                        "3 - Сверить отчеты\n\t" +
                        "4 - Вывести информацию о всех месячных отчетах\n\t" +
                        "5 - Вывести информацию о годовом отчете\n\t" +
                        "6 - Выход");
                userInput =  scanner.nextInt();
            }

            if (userInput < 0 || userInput > 6){
                System.out.println(FinalVariables.ANSI_RED + "Такого пункта в меню нет, повторите" + FinalVariables.ANSI_RESET);
                return 0;
            }

            return userInput;
    }
    public static void menuSwitch(int userInput){
        switch (userInput){
            case (1):
                if (monthDataArrayList.size() == 0){
                    monthDataArrayList = accountingScanner.readAllMonthFiles();
                }

                System.out.println(FinalVariables.ANSI_BLUE + "Месячные отчеты считаны, было найдено отчетов: " + monthDataArrayList.size() + FinalVariables.ANSI_RESET);
                break;
            case (2):
                if (yearDataArrayList .size() == 0){
                    yearDataArrayList = accountingScanner.readAllYearFiles();
                }
                System.out.println(FinalVariables.ANSI_BLUE + "Годовые отчеты считаны, было найдено отчетов: " + yearDataArrayList.size() + FinalVariables.ANSI_RESET);
                break;
            case (3):
                if (!monthDataArrayList.isEmpty() && !yearDataArrayList.isEmpty()){
                    //System.out.println("Списки не пусты, будем работать");
                    if (financeHandler == null){
                        financeHandler = new FinanceHandler(yearDataArrayList, monthDataArrayList);
                    }
                    //financeHandler.showMonthExpenses();
                    financeHandler.compareYearAndMonth();


                }
                else {
                    System.out.println(FinalVariables.ANSI_RED + "Сначала считайте месячные и годовые отчеты" + FinalVariables.ANSI_RESET);
                }
                break;
            case (4):
                if (!monthDataArrayList.isEmpty() && !yearDataArrayList.isEmpty()){
                    //System.out.println("Списки не пусты, будем работать");
                    if (financeHandler == null){
                        financeHandler = new FinanceHandler(yearDataArrayList, monthDataArrayList);
                    }
                    System.out.println(" ");
                    financeHandler.showMonthExpenses();



                }
                else {
                    System.out.println(FinalVariables.ANSI_RED + "Сначала считайте месячные и годовые отчеты" + FinalVariables.ANSI_RESET);
                }

                break;
            case (5):
                if (!monthDataArrayList.isEmpty() && !yearDataArrayList.isEmpty()){
                    //System.out.println("Списки не пусты, будем работать");
                    if (financeHandler == null){
                        financeHandler = new FinanceHandler(yearDataArrayList, monthDataArrayList);
                    }
                    financeHandler.showYearData();
                }
                else {
                    System.out.println(FinalVariables.ANSI_RED + "Сначала считайте месячные и годовые отчеты" + FinalVariables.ANSI_RESET);
                }
                break;
        }

    }

}

