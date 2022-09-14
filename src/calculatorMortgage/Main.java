package calculatorMortgage;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static short MONTHS_IN_YEAR = 12;
    final static short PERCENT = 100;

    public static void main(String[] args) {

        int principal = (int)readNumber("Principal: ", 1_000, 1_000_000);
        float interestRate = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, interestRate, years);
        printPaymentSchedule(principal, interestRate, years);
    }

    private static void printMortgage(int principal, float interestRate, byte years) {
        double mortgage = calculateMortgage(principal, interestRate, years);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("-------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    public static void printPaymentSchedule(int principal, float interestRate, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, interestRate, years,month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }
    public static double readNumber(String info, int minValue, int maxValue){
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true){
            System.out.print(info);
            value = scanner.nextDouble();
            if(value >= minValue && value <= maxValue)
                break;
            System.out.println("Enter value between " + minValue + " and " + maxValue);
        }
        return value;
    }

    public static double calculateBalance(
            int principle,
            float annualInterest,
            int years,
            short numberOfPaymentsMade){

        float monthlyInterest = annualInterest/MONTHS_IN_YEAR/PERCENT;
        int numberOfPayments = MONTHS_IN_YEAR*years;

        double balance = principle
                *(Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                /(Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return balance;

    }

    public static double calculateMortgage(
            int principle,
            float annualInterest,
            int years){

        float monthlyInterest = annualInterest/MONTHS_IN_YEAR/PERCENT;
        int numberOfPayments = MONTHS_IN_YEAR*years;

        return principle
                *(monthlyInterest*Math.pow(1+monthlyInterest,numberOfPayments)
                /(Math.pow(1+monthlyInterest,numberOfPayments)-1));
    }





}
