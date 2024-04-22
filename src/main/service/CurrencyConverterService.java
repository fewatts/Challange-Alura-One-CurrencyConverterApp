package service;

import java.net.http.HttpResponse;

/**
 * Service class responsible for currency conversion operations.
 */
public class CurrencyConverterService {

    /**
     * Starts the currency converter.
     */
    public static void startCurrencyConverter() {
        while (true) {

            int option = InputOutputService.getUserInput();

            if (option != 11) {
                String[] optionAndAmount = InputOutputService.getBaseCurrency(option);

                String[] baseAndTarget = InputOutputService
                        .determineBaseAndTarget(Integer.parseInt(optionAndAmount[0]));

                HttpResponse<String> APIResponse = CurrencyApiService.getExchangeRate(optionAndAmount[1],
                        baseAndTarget[0], baseAndTarget[1], baseAndTarget[2], baseAndTarget[3]);

                String formattedResult = CurrencyApiService.generateConversionFromResponse(APIResponse);

                if (!formattedResult.equalsIgnoreCase("null")) {
                    InputOutputService.printConversion(optionAndAmount[1],
                            baseAndTarget[0], baseAndTarget[3], baseAndTarget[2], baseAndTarget[1], formattedResult);
                }

            } else {
                LogService.printAllLogs();
            }
            if (InputOutputService.closeApp()) {
                return;
            }

        }

    }

}