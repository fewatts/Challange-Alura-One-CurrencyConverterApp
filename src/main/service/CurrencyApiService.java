package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import model.Currency;

/**
 * Service class responsible for calling the currency conversion API.
 */
public class CurrencyApiService {

    private static final String API_URL_FORMAT = "https://v6.exchangerate-api.com/v6/c49876973d6fc7cc5f92bf7a/pair/%s/%s/%s";

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
    public static String generateConversionFromResponse(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            Currency exchangeRateResponse = gson.fromJson(response.body(), Currency.class);
            double conversionResult = exchangeRateResponse.conversion_result();
            String formattedResult = String.format("%.2f", conversionResult);

            return formattedResult;
        } else {
            if (response.statusCode() == 429) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("    Request quota exhausted");
                System.out.println("    Try next month :(");
                System.out.println("    or change the URL Token");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                return "null";
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Failed to get response from API.");
            System.out.println(" Status code: " + response.statusCode());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return "null";
        }

    }

}