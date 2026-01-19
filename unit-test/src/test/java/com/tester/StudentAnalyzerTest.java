package com.tester;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();
    @Test
    @DisplayName("EP-C1: Input List is Null - Should handle gracefully or throw")
    public void testContainer_NullInput() {
        assertEquals(0, analyzer.countExcellentStudents(null), "Null list should return 0 students");
        assertEquals(0.0, analyzer.calculateValidAverage(null), 0.001, "Null list should return 0.0 average");
    }

    @Test
    @DisplayName("EP-C2: Input List is Empty - Should return 0")
    public void testContainer_EmptyInput() {
        List<Double> input = Collections.emptyList();
        assertEquals(0, analyzer.countExcellentStudents(input));
        assertEquals(0.0, analyzer.calculateValidAverage(input), 0.001);
    }

    @Test
    @DisplayName("EP-D1: All elements are Invalid Low (< 0)")
    public void testElements_AllInvalidLow() {
        List<Double> input = Arrays.asList(-1.5, -10.0, -0.1);
        
        assertEquals(0, analyzer.countExcellentStudents(input), "Should ignore negative numbers");
        assertEquals(0.0, analyzer.calculateValidAverage(input), 0.001, "Average of invalid numbers should be 0");
    }

    @Test
    @DisplayName("EP-D2: All elements are Valid but Non-Excellent (0 <= x < 8)")
    public void testElements_AllValidNonExcellent() {
        List<Double> input = Arrays.asList(5.0, 6.5, 7.9);
        
        assertEquals(0, analyzer.countExcellentStudents(input), "Should count 0 excellent students");
        assertEquals(6.467, analyzer.calculateValidAverage(input), 0.01, "Should calculate average of all valid scores");
    }

    @Test
    @DisplayName("EP-D3: All elements are Valid Excellent (8 <= x <= 10)")
    public void testElements_AllValidExcellent() {
        List<Double> input = Arrays.asList(8.0, 9.5, 10.0);
        
        assertEquals(3, analyzer.countExcellentStudents(input), "All 3 should be excellent");
        assertEquals(9.17, analyzer.calculateValidAverage(input), 0.01);
    }

    @Test
    @DisplayName("EP-D4: All elements are Invalid High (> 10)")
    public void testElements_AllInvalidHigh() {
        List<Double> input = Arrays.asList(10.1, 15.0, 100.0);
        
        assertEquals(0, analyzer.countExcellentStudents(input), "Should ignore scores > 10");
        assertEquals(0.0, analyzer.calculateValidAverage(input), 0.001);
    }

    @Test
    @DisplayName("EP-Mixed: Combination of Valid Excellent, Non-Excellent and Invalid")
    public void testMixed_ComplexScenario() {
        List<Double> input = Arrays.asList(
            -5.0,  
            5.0,   
            9.0,  
            11.0,  
            8.0    
        );

        assertEquals(2, analyzer.countExcellentStudents(input), "Should only count valid excellent scores");
        assertEquals(7.33, analyzer.calculateValidAverage(input), 0.01, "Average should be 22/3");
    }

    @Test
    @DisplayName("BVA: Strict Boundary Values for 0, 8, and 10")
    public void testBoundaries_Strict() {
        List<Double> minBoundary = Arrays.asList(-0.001, 0.0, 0.001);
        assertEquals(0, analyzer.countExcellentStudents(minBoundary), "At 0 is not excellent");
        assertEquals(0.0005, analyzer.calculateValidAverage(minBoundary), 0.0001, "Should accept 0.0 and 0.001");

        List<Double> excellentBoundary = Arrays.asList(7.999, 8.0, 8.001);
        assertEquals(2, analyzer.countExcellentStudents(excellentBoundary), "8.0 and 8.001 should be excellent");
        assertEquals(8.0, analyzer.calculateValidAverage(excellentBoundary), 0.01);

        List<Double> maxBoundary = Arrays.asList(9.999, 10.0, 10.001);
        assertEquals(2, analyzer.countExcellentStudents(maxBoundary), "9.999 and 10.0 are excellent");
        assertEquals(9.9995, analyzer.calculateValidAverage(maxBoundary), 0.0001, "Should ignore 10.001");
    }
}