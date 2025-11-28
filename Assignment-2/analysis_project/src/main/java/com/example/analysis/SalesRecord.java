package com.example.analysis;

import java.time.LocalDate;

public class SalesRecord {
    private final LocalDate date;
    private final String region;
    private final String product;
    private final int quantity;
    private final double unitPrice;

    public SalesRecord( LocalDate date, String region, String product, int quantity, double unitPrice) {
        this.date = date;
        this.region = region;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRegion() {
        return region;
    }

    public String getProduct() {
        return product;
    }

    public double getRevenue() {
        return quantity * unitPrice;
    }
}
