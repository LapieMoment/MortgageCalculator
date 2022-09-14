package calculatorMortgage;

import java.util.Scanner;

class CheckValue {
    int principal = (int) readNumber("Principal: ", 1_000, 1_000_000);
    float interestRate = (float) readNumber("Annual Interest Rate: ", 1, 30);
    byte years = (byte) readNumber("Period (Years): ", 1, 30);

    double readNumber(String info, int minValue, int maxValue){
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
}
