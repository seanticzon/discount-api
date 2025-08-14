package org.example.discountservice.model;

public class DiscountResponse {
    private double originalTotal;
    private String discountName;
    private double discountPercentage;
    private double discountAmount;
    private double finalTotal;

    public DiscountResponse(double originalTotal, String discountName, double discountPercentage, double discountAmount, double finalTotal) {
        this.originalTotal = originalTotal;
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.finalTotal = finalTotal;
    }

    public double getOriginalTotal() {
        return originalTotal;
    }
    public String getDiscountName() {
        return discountName;
    }
    public double getDiscountPercentage() {
        return discountPercentage;
    }
    public double getDiscountAmount() {
        return discountAmount;
    }
    public double getFinalTotal() {
        return finalTotal;
    }
}
