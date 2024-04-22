package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Log;

/**
 * Service class responsible for managing logs of currency conversion.
 */
public class LogService {

    private static final List<Log> logs = new ArrayList<>();

    /**
     * Creates a log entry for a currency conversion.
     * 
     * @param originalValue   The original value entered by the user.
     * @param baseSymbol      The symbol of the base currency.
     * @param targetSymbol    The symbol of the target currency.
     * @param baseCurrency    The code of the base currency.
     * @param targetCurrency  The code of the target currency.
     * @param formattedResult The formatted result of the conversion.
     */
    public static void createConversionLog(String originalValue, String baseSymbol, String targetSymbol,
            String baseCurrency, String targetCurrency, String formattedResult) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        Log log = new Log(currentDateTime, originalValue, baseSymbol, baseCurrency,
                targetSymbol, targetCurrency, formattedResult);
        logs.add(log);
    }

    /**
     * Prints all conversion logs.
     */
    public static void printAllLogs() {
        if (logs.isEmpty()) {
            System.out.println("No logs available.");
        } else {
            for (Log l : logs) {
                l.printLog();
            }

        }

    }

}