package org.example.discountservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public class BasketDiscountRequest {
    @NotBlank(message = "discountName is required")
    private String discountName;

    @PositiveOrZero(message = "subtotal must be >= 0")
    private double subtotal;

    @NotNull(message = "items list is required")
    @Valid
    private List<LineItem> items;

    // Getters and setters
    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public static class LineItem {
        @NotBlank(message = "id is required")
        private String id;

        @NotBlank(message = "name is required")
        private String name;

        @PositiveOrZero(message = "qty must be >= 0")
        private int qty;

        @PositiveOrZero(message = "unitPrice must be >= 0")
        private double unitPrice;

        @PositiveOrZero(message = "lineTotal must be >= 0")
        private double lineTotal;

        // Getters and setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getLineTotal() {
            return lineTotal;
        }

        public void setLineTotal(double lineTotal) {
            this.lineTotal = lineTotal;
        }
    }
}
