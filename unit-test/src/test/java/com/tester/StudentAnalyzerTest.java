package com.tester;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    @Test
    public void testCountExcellent_StandardCase() {
        List<Double> input = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(2, analyzer.countExcellentStudents(input));
    }

    @Test
    public void testCountExcellent_EmptyList() {
        List<Double> input = Collections.emptyList();
        assertEquals(0, analyzer.countExcellentStudents(input));
    }

    @Test
    public void testCountExcellent_BoundaryValues() {
        List<Double> input = Arrays.asList(0.0, 10.0);
        assertEquals(1, analyzer.countExcellentStudents(input));
    }

    @Test
    public void testCalculateValidAverage_StandardCase() {
        List<Double> input = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(8.17, analyzer.calculateValidAverage(input), 0.01);
    }

    @Test
    public void testCalculateValidAverage_AllInvalidScores() {
        List<Double> input = Arrays.asList(-2.0, 12.0);
        assertEquals(0.0, analyzer.calculateValidAverage(input), 0.001);
    }
}