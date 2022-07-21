import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String inputCurrency;
        String inputAmount;

        Scanner sc = new Scanner(System.in);

        for (String key : calculator.getRatesExchange().keySet()) {
            System.out.println(key);
        }

        System.out.println("Enter currency from above:");
        inputCurrency = sc.nextLine();
        System.out.println("Input amount");
        inputAmount = sc.nextLine();

        System.out.println(inputAmount + " EUR is " + calculator.convertCurrency(inputAmount, inputCurrency) + " " + inputCurrency.toUpperCase());
    }
}
