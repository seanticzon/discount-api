package org.example.discountservice.controller;

import jakarta.validation.Valid;
import org.example.discountservice.model.DiscountRequest;
import org.example.discountservice.model.DiscountResponse;
import org.example.discountservice.model.BasketDiscountRequest;
import org.example.discountservice.model.BasketDiscountResponse;
import org.example.discountservice.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    // POST /discount/apply - Legacy endpoint for backward compatibility
    @PostMapping("/apply")
    public DiscountResponse applyDiscount(@Valid @RequestBody DiscountRequest request) {
        return discountService.applyDiscount(request.getTotal(), request.getDiscountName());
    }

    // POST /discount/applyBasket - New basket-based endpoint with enhanced logic
    @PostMapping("/applyBasket")
    public ResponseEntity<BasketDiscountResponse> applyDiscountToBasket(
            @Valid @RequestBody BasketDiscountRequest request) {

        double originalSubtotal = request.getSubtotal();
        String discountName = request.getDiscountName();
        List<BasketDiscountRequest.LineItem> items = request.getItems();

        // Enhanced discount logic based on discount type
        BasketDiscountResponse response;

        // Handle special item-based discounts
        if ("BOGO Value".equalsIgnoreCase(discountName)) {
            response = applyBogoDiscount(originalSubtotal, discountName, items);
        } else {
            // For other discounts, use your existing service logic
            DiscountResponse basicDiscount = discountService.applyDiscount(
                    originalSubtotal,
                    discountName
            );

            // Convert to basket response format
            response = new BasketDiscountResponse(
                    basicDiscount.getOriginalTotal(),          // originalSubtotal
                    basicDiscount.getDiscountName(),           // discountName
                    basicDiscount.getDiscountPercentage(),     // discountPercentage
                    basicDiscount.getDiscountAmount(),         // discountAmount
                    basicDiscount.getFinalTotal()              // discountedSubtotal
            );
        }

        return ResponseEntity.ok(response);
    }

    // BOGO (Buy One Get One 50% off) logic using individual items
    private BasketDiscountResponse applyBogoDiscount(double originalSubtotal, String discountName,
                                                     List<BasketDiscountRequest.LineItem> items) {
        double totalDiscountAmount = 0.0;

        // Apply BOGO logic: For every 2 items of the same type, get 50% off the cheaper one
        for (BasketDiscountRequest.LineItem item : items) {
            if (item.getQty() >= 2) {
                // Calculate how many pairs we have
                int pairs = item.getQty() / 2;
                // Apply 50% discount to the cheaper item in each pair
                double discountPerPair = item.getUnitPrice() * 0.5;
                totalDiscountAmount += pairs * discountPerPair;
            }
        }

        double discountPercentage = originalSubtotal > 0 ? (totalDiscountAmount / originalSubtotal) * 100 : 0;
        double discountedSubtotal = Math.max(0.0, originalSubtotal - totalDiscountAmount);

        return new BasketDiscountResponse(
                originalSubtotal,
                discountName,
                Math.round(discountPercentage * 100.0) / 100.0, // Round to 2 decimal places
                Math.round(totalDiscountAmount * 100.0) / 100.0,
                Math.round(discountedSubtotal * 100.0) / 100.0
        );
    }
}