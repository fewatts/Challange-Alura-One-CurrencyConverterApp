package service;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Service class responsible for handling user input related to currency
 * conversion.
 */
public class CurrencyInputOutputService {

    private static final Scanner scan = new Scanner(System.in);

    /**
     * Gets the user input for selecting the currency conversion option.
     *
     * @return The selected option.
     */
    public static int getUserInput() {
        int option;
        boolean validInput = false;
        do {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            Select the currency:
                            1 ~~ BRL -> EUR.
                            2 ~~ BRL -> USD.
                            3 ~~ BRL -> GBP.
                            4 ~~ BRL -> JPY.
                            5 ~~ BRL -> CHF.
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            6 ~~ EUR -> BRL.
                            7 ~~ USD -> BRL.
                            8 ~~ GBP -> BRL.
                            9 ~~ JPY -> BRL.
                            10 ~ CHF -> BRL.
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            11 ~ Print all Logs.
                    Option:
                    """);
            try {
                option = scan.nextInt();

                if (option >= 1 && option <= 11)
                    validInput = true;
                else
                    System.out.println("Invalid option. Please enter a number between 1 and 10.");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.next();
                option = 0;
            }

        } while (!validInput);
        return option;
    }

    /**
     * Prompts the user to input a positive number between 1 and 10,000,000 and
     * returns it as a string.
     *
     * @param option The selected currency conversion option.
     * @return An array containing the selected option and amount as strings.
     */
    public static String[] getBaseCurrency(int option) {
        double value;
        do {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       Currency Converter App. Ltd
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Type the value (positive number between 1 and 10,000,000):
                       """);
            while (!scan.hasNextDouble()) {
                System.out.println("Please enter a valid positive number:");
                scan.next();
            }
            value = scan.nextDouble();
        } while (value <= 0 || value > 10000000);
        return new String[] { String.valueOf(option), String.valueOf(value) };
    }

    /**
     * Closes the application based on user input.
     *
     * @return True if the user wants to exit, false otherwise.
     */
    public static boolean closeApp() {
        String input;
        do {
            System.out.println("Exit? (Y/N)");
            input = scan.next();
        } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("Closing Application...");
            scan.close();
            return true;
        }
        return false;
    }

    /**
     * Prints the conversion result.
     * 
     * @param originalValue   the original value to be converted
     * @param baseSymbol      the symbol for the base currency
     * @param targetSymbol    the symbol for the target currency
     * @param baseCurrency    the base currency code
     * @param targetCurrency  the target currency code
     * @param formattedResult the formatted conversion result
     */
    public static void printConversion(String originalValue, String baseSymbol, String targetSymbol, String baseCurrency,
            String targetCurrency, String formattedResult) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(originalValue.replace(".", ",") + " " + baseSymbol + " " + baseCurrency + " to "
                + targetCurrency + " --> "
                + formattedResult + " " + targetSymbol);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LogService.createConversionLog(originalValue, baseSymbol, baseCurrency,
                targetSymbol, targetCurrency, formattedResult);
    }

}