package service;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Service class responsible for handling user input/output related to currency
 * conversion.
 */
public class InputOutputService {

    private static final Scanner scan = new Scanner(System.in);
    private static final List<String> currencyCodes = Arrays.asList("BRL", "EUR", "USD", "GBP", "JPY", "CHF");
    private static final List<String> currencySymbols = Arrays.asList("R$", "€", "US$", "£", "¥", "Fr");

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
                    System.out.println("Invalid option. Please enter a");
                    System.out.println("number between 1 and 10.");

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
                    Type the value (positive number 
                    between 1 and 10,000,000):
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
     * Determines the base and target currencies based on the provided option.
     * This method does not call any external API, it simply determines the order of
     * currency conversion
     * based on the given integer option.
     *
     * @param option The selected currency conversion option. Should be an integer
     *               between 1 and 10.
     * @return An array containing base and target currency codes and symbols.
     *         Index 0 and 1 contain the base and target currency codes,
     *         respectively.
     *         Index 2 and 3 contain the base and target currency symbols,
     *         respectively.
     */
    public static String[] determineBaseAndTarget(int option) {
        String[] result = new String[4];
        switch (option) {
            case 1 -> {
                result[0] = currencyCodes.get(0);
                result[1] = currencyCodes.get(1);
                result[2] = currencySymbols.get(0);
                result[3] = currencySymbols.get(1);
            }
            case 2 -> {
                result[0] = currencyCodes.get(0);
                result[1] = currencyCodes.get(2);
                result[2] = currencySymbols.get(0);
                result[3] = currencySymbols.get(2);
            }
            case 3 -> {
                result[0] = currencyCodes.get(0);
                result[1] = currencyCodes.get(3);
                result[2] = currencySymbols.get(0);
                result[3] = currencySymbols.get(3);
            }
            case 4 -> {
                result[0] = currencyCodes.get(0);
                result[1] = currencyCodes.get(4);
                result[2] = currencySymbols.get(0);
                result[3] = currencySymbols.get(4);
            }
            case 5 -> {
                result[0] = currencyCodes.get(0);
                result[1] = currencyCodes.get(5);
                result[2] = currencySymbols.get(0);
                result[3] = currencySymbols.get(5);
            }
            case 6 -> {
                result[0] = currencyCodes.get(1);
                result[1] = currencyCodes.get(0);
                result[2] = currencySymbols.get(1);
                result[3] = currencySymbols.get(0);
            }
            case 7 -> {
                result[0] = currencyCodes.get(2);
                result[1] = currencyCodes.get(0);
                result[2] = currencySymbols.get(2);
                result[3] = currencySymbols.get(0);
            }
            case 8 -> {
                result[0] = currencyCodes.get(3);
                result[1] = currencyCodes.get(0);
                result[2] = currencySymbols.get(3);
                result[3] = currencySymbols.get(0);
            }
            case 9 -> {
                result[0] = currencyCodes.get(4);
                result[1] = currencyCodes.get(0);
                result[2] = currencySymbols.get(4);
                result[3] = currencySymbols.get(0);
            }
            case 10 -> {
                result[0] = currencyCodes.get(5);
                result[1] = currencyCodes.get(0);
                result[2] = currencySymbols.get(5);
                result[3] = currencySymbols.get(0);
            }

        }
        return result;
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