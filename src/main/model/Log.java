package model;

import java.time.LocalDateTime;

/**
 * The Log class represents a log entry for currency conversion.
 */
public class Log {
    private static int nextId = 1;
    private int id;
    private LocalDateTime date;
    private String originalValue;
    private String baseSymbol;
    private String baseCurrency;
    private String targetSymbol;
    private String targetCurrency;
    private String formattedResult;

    /**
     * Constructs a Log object with the specified parameters.
     *
     * @param currentDateTime   The date and time of the log entry.
     * @param originalValue2    The original value before conversion.
     * @param baseSymbol2       The symbol of the base currency.
     * @param targetSymbol2     The symbol of the target currency.
     * @param baseCurrency2     The name of the base currency.
     * @param targetCurrency    The name of the target currency.
     * @param formattedResult2  The formatted result after conversion.
     */
    public Log(LocalDateTime currentDateTime, String originalValue2, String baseSymbol2, String targetSymbol2,
               String baseCurrency2, String targetCurrency, String formattedResult2) {
        this.id = nextId++;
        this.date = currentDateTime;
        this.originalValue = originalValue2;
        this.baseSymbol = baseSymbol2;
        this.baseCurrency = baseCurrency2;
        this.targetSymbol = targetSymbol2;
        this.targetCurrency = targetCurrency;
        this.formattedResult = formattedResult2;
    }

    /**
     * Gets the ID of the log entry.
     *
     * @return The ID of the log entry.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the date and time of the log entry.
     *
     * @return The date and time of the log entry.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets the original value before conversion.
     *
     * @return The original value before conversion.
     */
    public String getOriginalValue() {
        return originalValue;
    }

    /**
     * Gets the symbol of the base currency.
     *
     * @return The symbol of the base currency.
     */
    public String getBaseSymbol() {
        return baseSymbol;
    }

    /**
     * Gets the name of the base currency.
     *
     * @return The name of the base currency.
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Gets the symbol of the target currency.
     *
     * @return The symbol of the target currency.
     */
    public String getTargetSymbol() {
        return targetSymbol;
    }

    /**
     * Gets the name of the target currency.
     *
     * @return The name of the target currency.
     */
    public String getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * Gets the formatted result after conversion.
     *
     * @return The formatted result after conversion.
     */
    public String getFormattedResult() {
        return formattedResult;
    }

    /**
     * Prints the log entry to the console.
     */
    public void printLog() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Log ID: " + this.getId());
        System.out.println("Date: " + this.getDate().getDayOfMonth() + "/" + this.getDate().getMonthValue() + "/"
                + this.getDate().getYear());
        System.out.println("Time: " + this.getDate().getHour() + ":" + this.getDate().getMinute() + ":"
                + this.getDate().getSecond());
        System.out.println("Conversion info:");
        System.out.println(
                "Input value: " + this.getOriginalValue() + " " + this.getBaseSymbol() + " " + this.getBaseCurrency());
        System.out.println("Output value: " + this.getFormattedResult() + " " + this.getTargetSymbol() + " "
                + this.getTargetCurrency());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}