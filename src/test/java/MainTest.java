import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        // Arrange
        String[] test = new String[] { "test" };

        // Act
        Runnable actual = () -> Main.main(test);

        // Assert
        assertDoesNotThrow(actual::run);
    }
}