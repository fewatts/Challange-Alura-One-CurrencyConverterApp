package controller;

import service.CurrencyConverterService;

/**
 * Main controller class for the Currency Converter Application.
 */
public class CurrencyConverterController {

    /**
     * Starts the currency converter application.
     */
    public static void start() {
        CurrencyConverterService.startCurrencyConverter();
    }
    
}