package org.example.discountservice.service;

import org.example.discountservice.model.DiscountResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DiscountService {

    public DiscountResponse applyDiscount(double total, String discountName) {
        String safeName = discountName == null ? "" : discountName.trim();
        String key = safeName.toLowerCase();

        double discountPercentage = switch (key) {
            case "cash payment discount" -> 1;
            case "silver loyalty tier" -> 5;
            case "gold loyalty tier" -> 10;
            case "bogo value" -> 50;
            case "digital coupon average" -> 20;
            default -> 0;
        };

        BigDecimal totalBd = BigDecimal.valueOf(Math.max(0, total));
        BigDecimal percentBd = BigDecimal.valueOf(discountPercentage).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
        BigDecimal discountAmountBd = totalBd.multiply(percentBd).setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalTotalBd = totalBd.subtract(discountAmountBd).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);

        return new DiscountResponse(
                totalBd.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                safeName.isEmpty() ? "No Discount" : safeName,
                discountPercentage,
                discountAmountBd.doubleValue(),
                finalTotalBd.doubleValue()
        );
    }
}
