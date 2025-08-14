package org.example.discountservice.model;

public class BasketDiscountResponse {
    private double originalSubtotal;
    private String discountName;
    private double discountPercentage;
    private double discountAmount;
    private double discountedSubtotal;

    // Constructors
    public BasketDiscountResponse() {}

    public BasketDiscountResponse(double originalSubtotal, String discountName,
                                  double discountPercentage, double discountAmount,
                                  double discountedSubtotal) {
        this.originalSubtotal = originalSubtotal;
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.discountedSubtotal = discountedSubtotal;
    }

    // Getters and setters
    public double getOriginalSubtotal() {
        return originalSubtotal;
    }

    public void setOriginalSubtotal(double originalSubtotal) {
        this.originalSubtotal = originalSubtotal;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountedSubtotal() {
        return discountedSubtotal;
    }

    public void setDiscountedSubtotal(double discountedSubtotal) {
        this.discountedSubtotal = discountedSubtotal;
    }
}