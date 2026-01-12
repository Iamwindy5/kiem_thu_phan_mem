package com.tester;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class StudentAnalyzerTest {
    StudentAnalyzer analyzer = new StudentAnalyzer();

    @Test
    public void testCountExcellentStudents() {
        assertEquals(2, analyzer.countExcellentStudents(Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0)));
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
        assertEquals(1, analyzer.countExcellentStudents(Arrays.asList(0.0, 10.0)));
    }

    @Test
    public void testCalculateValidAverage() {
        assertEquals(8.17, analyzer.calculateValidAverage(Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0)), 0.01);
        assertEquals(0.0, analyzer.calculateValidAverage(Arrays.asList(-2.0, 12.0)));
    }
}