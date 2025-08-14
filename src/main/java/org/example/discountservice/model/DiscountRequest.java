package org.example.discountservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class DiscountRequest {
    @PositiveOrZero(message = "total must be >= 0")
    private double total;

    @NotBlank(message = "discountName is required")
    private String discountName;

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public String getDiscountName() {
        return discountName;
    }
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }
}
