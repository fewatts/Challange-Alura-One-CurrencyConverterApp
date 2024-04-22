package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import model.Currency;

/**
 * Service class responsible for calling the currency conversion API.
 */
public class CurrencyApiService {

    private static final String API_URL_FORMAT = "https://v6.exchangerate-api.com/v6/c49876973d6fc7cc5f92bf7a/pair/%s/%s/%s";
    private static final List<String> currencyCodes = Arrays.asList("BRL", "EUR", "USD", "GBP", "JPY", "CHF");
    private static final List<String> currencySymbols = Arrays.asList("R$", "€", "US$", "£", "¥", "Fr");

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
     * Fetches exchange rate from the API.
     * 
     * @param originalValue  The original value to be converted.
     * @param baseCurrency   The base currency code.
     * @param targetCurrency The target currency code.
     * @param baseSymbol     The symbol for the base currency.
     * @param targetSymbol   The symbol for the target currency.
     * @return The HTTP response containing the exchange rate data.
     */
    public static HttpResponse<String> getExchangeRate(String originalValue, String baseCurrency, String targetCurrency,
            String baseSymbol, String targetSymbol) {
        String apiUrl = String.format(API_URL_FORMAT, baseCurrency, targetCurrency, originalValue);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Handles the API response, parses it, and returns the conversion result.
     * 
     * @param response The HTTP response from the API.
     * @return The formatted conversion result as a string, or "NULL" if the
     *         response was unsuccessful.
     */
    public static String handleResponse(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            Currency exchangeRateResponse = gson.fromJson(response.body(), Currency.class);
            double conversionResult = exchangeRateResponse.conversion_result();
            String formattedResult = String.format("%.2f", conversionResult);

            return formattedResult;
        } else {
            System.out.println("Failed to get response from API. Status code: " + response.statusCode());
            System.out.println(response);
            return "NULL";
        }

    }

}