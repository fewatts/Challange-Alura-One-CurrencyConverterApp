package model;

/**
 * Represents a currency conversion response from the API.
 */
public record Currency(
                /**
                 * The result of the conversion request.
                 */
                String result,
                /**
                 * The code of the base currency.
                 */
                String base_code,
                /**
                 * The code of the target currency.
                 */
                String target_code,
                /**
                 * The conversion rate from the base currency to the target currency.
                 */
                double conversion_rate,
                /**
                 * The result of the conversion.
                 */
                double conversion_result) {}