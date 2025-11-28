package com.example.analysis;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.*;

public class SalesAnalyzer {
    public static double totalRevenue(List<SalesRecord> salesRecords) { //calculating total revenue
        return salesRecords.stream().mapToDouble(SalesRecord::getRevenue).sum();
    }

    public static Map<String, Double> revenueByRegion(List<SalesRecord> salesRecords) { //calculating revenue by each region
        return salesRecords.stream().collect(Collectors.groupingBy(
                SalesRecord::getRegion, Collectors.summingDouble(SalesRecord::getRevenue)));
    }

    public static Map<String, Double> revenueByProduct(List<SalesRecord> salesRecords) { //calculating revenue by product
        return salesRecords.stream().collect(Collectors.groupingBy(
                SalesRecord::getProduct, Collectors.summingDouble(SalesRecord::getRevenue)));
    }

    public static double averageOrderValue(List<SalesRecord> salesRecords) { //calculating average value of each order
        return salesRecords.stream().mapToDouble(SalesRecord::getRevenue).average().orElse(0);
    }

    public static Optional<Map.Entry<String, Double>> bestProductByRevenue(List<SalesRecord> salesRecords) {
        //determining the best product based on revenue
        return revenueByProduct(salesRecords).entrySet().stream().max(Map.Entry.comparingByValue());
    }

    public static Map<YearMonth, Double> revenueByMonth(List<SalesRecord> salesRecords) { //calculating revenue by month
        return salesRecords.stream().collect(Collectors.groupingBy(
                x -> YearMonth.from(x.getDate()), Collectors.summingDouble(SalesRecord::getRevenue)));
    }
}
