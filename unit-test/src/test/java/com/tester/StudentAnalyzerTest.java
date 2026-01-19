package com.tester;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    // ==========================================
    // LEVEL 1: CONTAINER PARTITIONS (List Level)
    // ==========================================

    @Test
    @DisplayName("EP-C1: Input List is Null - Should handle gracefully or throw")
    public void testContainer_NullInput() {
        // Tùy thuộc vào spec, nếu yêu cầu ném lỗi thì dùng assertThrows
        // Ở đây giả định code an toàn sẽ trả về 0
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

    // ==========================================
    // LEVEL 2: ELEMENT PARTITIONS (Pure Data)
    // ==========================================

    @Test
    @DisplayName("EP-D1: All elements are Invalid Low (< 0)")
    public void testElements_AllInvalidLow() {
        // List chỉ chứa số âm
        List<Double> input = Arrays.asList(-1.5, -10.0, -0.1);
        
        assertEquals(0, analyzer.countExcellentStudents(input), "Should ignore negative numbers");
        assertEquals(0.0, analyzer.calculateValidAverage(input), 0.001, "Average of invalid numbers should be 0");
    }

    @Test
    @DisplayName("EP-D2: All elements are Valid but Non-Excellent (0 <= x < 8)")
    public void testElements_AllValidNonExcellent() {
        // List chỉ chứa điểm trung bình/khá
        List<Double> input = Arrays.asList(5.0, 6.5, 7.9);
        
        assertEquals(0, analyzer.countExcellentStudents(input), "Should count 0 excellent students");
        // (5.0 + 6.5 + 7.9) / 3 = 6.466...
        assertEquals(6.467, analyzer.calculateValidAverage(input), 0.01, "Should calculate average of all valid scores");
    }

    @Test
    @DisplayName("EP-D3: All elements are Valid Excellent (8 <= x <= 10)")
    public void testElements_AllValidExcellent() {
        // List chỉ chứa điểm giỏi
        List<Double> input = Arrays.asList(8.0, 9.5, 10.0);
        
        assertEquals(3, analyzer.countExcellentStudents(input), "All 3 should be excellent");
        // (8.0 + 9.5 + 10.0) / 3 = 9.166...
        assertEquals(9.17, analyzer.calculateValidAverage(input), 0.01);
    }

    @Test
    @DisplayName("EP-D4: All elements are Invalid High (> 10)")
    public void testElements_AllInvalidHigh() {
        // List chỉ chứa điểm lố > 10
        List<Double> input = Arrays.asList(10.1, 15.0, 100.0);
        
        assertEquals(0, analyzer.countExcellentStudents(input), "Should ignore scores > 10");
        assertEquals(0.0, analyzer.calculateValidAverage(input), 0.001);
    }

    // ==========================================
    // LEVEL 3: MIXED PARTITIONS (Integration)
    // ==========================================

    @Test
    @DisplayName("EP-Mixed: Combination of Valid Excellent, Non-Excellent and Invalid")
    public void testMixed_ComplexScenario() {
        // Kịch bản thực tế: Trộn lẫn tất cả các vùng
        List<Double> input = Arrays.asList(
            -5.0,  // Invalid Low (Ignore)
            5.0,   // Valid Non-Excellent
            9.0,   // Valid Excellent
            11.0,  // Invalid High (Ignore)
            8.0    // Valid Excellent (Boundary)
        );

        // Valid scores: 5.0, 9.0, 8.0 (Tổng = 22.0, Count = 3)
        // Excellent scores: 9.0, 8.0 (Count = 2)

        assertEquals(2, analyzer.countExcellentStudents(input), "Should only count valid excellent scores");
        assertEquals(7.33, analyzer.calculateValidAverage(input), 0.01, "Average should be 22/3");
    }
}