package util;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//entire class copied from SP3

class TextUITest {
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        mockScanner = Mockito.mock(Scanner.class);
        TextUI.setScanner(mockScanner);
    }

    @Test
    void displayMsg() {
        // Arrange
        String message = "Test";

        // Act
        Runnable actual = () -> TextUI.displayMsg(message);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void displayEmptyMsg() {
        // Arrange
        String message =  "";

        // Act
        Runnable actual = () -> TextUI.displayMsg(message);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void displayNullMsg() {
        // Arrange

        // Act
        Runnable actual = () -> TextUI.displayMsg(null);

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void promptBinaryLowerCaseY() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("y");

        // Act
        boolean actual = TextUI.promptBinary(message);

        // Assert
        assertTrue(actual);
    }

    @Test
    void promptBinaryUpperCaseY() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("Y");

        // Act
        boolean actual = TextUI.promptBinary(message);

        // Assert
        assertTrue(actual);
    }

    @Test
    void promptBinaryLowerCaseN() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("n");

        // Act
        boolean actual = TextUI.promptBinary(message);

        // Assert
        assertFalse(actual);
    }

    @Test
    void promptBinaryUpperCaseN() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("N");

        // Act
        boolean actual = TextUI.promptBinary(message);

        // Assert
        assertFalse(actual);
    }

    @Test
    void promptBinaryInvalidInput() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("x", "y");

        // Act
        boolean actual = TextUI.promptBinary(message);

        // Assert
        assertTrue(actual);
    }

    @Test
    void promptNumeric() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("1");

        // Act
        int actual = TextUI.promptNumeric(message);

        // Assert
        assertEquals(1, actual);
    }

    @Test
    void promptNumericInvalidInput() {
        // Arrange
        String message = "test";
        when(mockScanner.nextLine()).thenReturn("x","1");

        // Act
        int actual = TextUI.promptNumeric(message);

        // Assert
        assertEquals(1, actual);
    }

    @Test
    void promptText() {
        // Arrange
        String message = "test";
        String expected = "input";
        when(mockScanner.nextLine()).thenReturn(expected);

        // Act
        String actual = TextUI.promptText(message);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void promptChoice() {
        // Arrange
        String option1 = "Search movie";
        String option2 = "Search category";
        List<String> options = new ArrayList<>(Arrays.asList(option1, option2));
        int limit = 1;
        String message = "Select option: ";
        when(mockScanner.nextLine()).thenReturn("1");

        // Act
        List<String> actual = TextUI.promptChoice(options, limit, message);

        // Assert
        assertEquals(option1, actual.get(0));
    }

    @Test
    void promptChoiceInvalidInput() {
        // Arrange
        String option1 = "Search movie";
        String option2 = "Search category";
        List<String> options = new ArrayList<>(Arrays.asList(option1, option2));
        int limit = 1;
        String message = "Select option: ";
        when(mockScanner.nextLine()).thenReturn("invalid", "3", "1");

        // Act
        List<String> actual = TextUI.promptChoice(options, limit, message);

        // Assert
        assertEquals(option1, actual.get(0));
    }

    @Test
    void displayList() {
        // Arrange
        String option1 = "Search movie";
        String option2 = "Search category";
        List<String> options = new ArrayList<>(Arrays.asList(option1, option2));
        String message = "Select option: ";

        // Act
        Runnable actual = () -> TextUI.displayList(options, message);

        // Assert
        assertDoesNotThrow(actual::run);
    }
    @Test
    void displayEmptyList() {
        // Arrange
        List<String> options = new ArrayList<>();
        String message = "Select option: ";

        // Act
        Runnable actual = () -> TextUI.displayList(options, message);

        // Assert
        assertDoesNotThrow(actual::run);
    }
}