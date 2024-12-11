package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCalculatorTest {
    @Test
    void bmiCalculatorTest() {
        // Arrange
        float actual = 20.06173f;
        float weight = 65;
        float height = 180;

        // Act
        float result = HealthCalculator.bmiCalculator(weight,height);

        // Assert
        assertEquals(actual,result);
    }
    @Test
    void bmiCalculatorTest2() {
        // Arrange
        float actual = 50f;
        float weight = 200;
        float height = 200;

        // Act
        float result = HealthCalculator.bmiCalculator(weight,height);

        // Assert
        assertEquals(actual,result);
    }
    @Test
    void bmiCalculatorTest3() {
        // Arrange
        float actual = 20.83333333333333f;
        float weight = 30;
        float height = 120;

        // Act
        float result = HealthCalculator.bmiCalculator(weight,height);

        // Assert
        assertEquals(actual,result);
    }
}
