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

            int option = CurrencyInputOutputService.getUserInput();

            if (option != 11) {
                String[] optionAndAmount = CurrencyInputOutputService.getBaseCurrency(option);

                String[] baseAndTarget = CurrencyApiService
                        .determineBaseAndTarget(Integer.parseInt(optionAndAmount[0]));

                HttpResponse<String> APIResponse = CurrencyApiService.getExchangeRate(optionAndAmount[1],
                        baseAndTarget[0], baseAndTarget[1], baseAndTarget[2], baseAndTarget[3]);

                String formatedResult = CurrencyApiService.handleResponse(APIResponse);

                CurrencyInputOutputService.printConversion(optionAndAmount[1],
                baseAndTarget[0], baseAndTarget[3], baseAndTarget[2], baseAndTarget[1], formatedResult);
                
            } else {
                LogService.printAllLogs();
            }
            if (CurrencyInputOutputService.closeApp()) {
                return;
            }

        }

    }

}