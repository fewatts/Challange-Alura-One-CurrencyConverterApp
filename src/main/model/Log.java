package model;

import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetSymbol() {
        return targetSymbol;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public String getFormattedResult() {
        return formattedResult;
    }

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