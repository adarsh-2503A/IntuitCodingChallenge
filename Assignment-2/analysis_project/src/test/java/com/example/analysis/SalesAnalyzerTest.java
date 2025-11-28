package com.example.analysis;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SalesAnalyzerTest {

    private List<SalesRecord> sample() {
        List<SalesRecord> l = new ArrayList<>();

        l.add(new SalesRecord(
                LocalDate.of(2025, 1, 10),
                "Hyderabad",
                "Iphone",
                2,
                75000.0
        ));

        l.add(new SalesRecord(
                LocalDate.of(2025, 1, 11),
                "Bangalore",
                "Mac",
                1,
                150000.0
        ));

        l.add(new SalesRecord(
                LocalDate.of(2025, 2, 5),
                "Chennai",
                "Airpods",
                3,
                23000.0
        ));

        l.add(new SalesRecord(
                LocalDate.of(2025, 2, 7),
                "Hyderabad",
                "Ipad",
                6,
                40000.0
        ));

        return l;
    }

    @Test
    public void testTotalRevenue() {
        double expected = 150000 + 150000 + 69000 + 240000;
        assertEquals(expected, SalesAnalyzer.totalRevenue(sample()), 0.001);
    }

    @Test
    public void testRevenueByRegion() {
        Map<String, Double> r = SalesAnalyzer.revenueByRegion(sample());

        // Hyderabad: Iphone (150000) + Ipad (240000)
        assertEquals(150000 + 240000, r.get("Hyderabad"), 0.001);
        assertEquals(150000.0, r.get("Bangalore"), 0.001);
        assertEquals(69000.0, r.get("Chennai"), 0.001);
    }


    @Test
    public void testAverageOrderValue() {
        double expected = (150000 + 150000 + 69000 + 240000) / 4.0;
        assertEquals(expected, SalesAnalyzer.averageOrderValue(sample()), 0.001);
    }

    @Test
    public void testRevenueByProduct() {
        Map<String, Double> m = SalesAnalyzer.revenueByProduct(sample());

        assertEquals(150000.0, m.get("Iphone"), 0.001);
        assertEquals(150000.0, m.get("Mac"), 0.001);
        assertEquals(69000.0, m.get("Airpods"), 0.001);
        assertEquals(240000.0, m.get("Ipad"), 0.001);
    }

    @Test
    public void testBestProduct() {
        var best = SalesAnalyzer.bestProductByRevenue(sample());

        assertTrue(best.isPresent());
        assertEquals("Ipad", best.get().getKey());
        assertEquals(240000.0, best.get().getValue(), 0.001);
    }

    @Test
    public void testRevenueByMonth() {
        Map<YearMonth, Double> m = SalesAnalyzer.revenueByMonth(sample());

        assertEquals(150000 + 150000, m.get(YearMonth.of(2025, 1)), 0.001);
        assertEquals(69000 + 240000, m.get(YearMonth.of(2025, 2)), 0.001);
    }
}
