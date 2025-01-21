package de.thab.cqt.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SudokuCreatorTest {

    @Mock
    private SudokuSolver sudokuSolver; // Mock for SudokuSolver

    @Test
    void testCreateNewBoardWithValidPrefilledFields() {
        // Arrange
        SudokuCreator sudokuCreator = new SudokuCreator(sudokuSolver);
        int numberOfPrefilledFields = 20;

        // Mock the behavior of isSolvable to always return true
        when(sudokuSolver.isSolvable(any(int[][].class))).thenReturn(true);

        // Act
        int[][] board = sudokuCreator.createNewBoard(numberOfPrefilledFields);

        // Assert
        assertNotNull(board, "The board should not be null");
        assertEquals(9, board.length, "The board should have 9 rows");
        assertEquals(9, board[0].length, "Each row should have 9 columns");

        // Verify the number of non-zero fields
        int filledFields = countFilledFields(board);
        assertEquals(numberOfPrefilledFields, filledFields, "The board should have the correct number of prefilled fields");

        // Verify the mock was used
        verify(sudokuSolver, atLeastOnce()).isSolvable(any(int[][].class));
    }

    @Test
    void testCreateNewBoardThrowsExceptionForInvalidPrefilledFields() {
        // Arrange
        SudokuCreator sudokuCreator = new SudokuCreator(sudokuSolver);

        // Act & Assert for too few prefilled fields
        assertThrows(IllegalArgumentException.class, () -> sudokuCreator.createNewBoard(0),
                "Should throw exception for fewer than 1 prefilled field");

        // Act & Assert for too many prefilled fields
        assertThrows(IllegalArgumentException.class, () -> sudokuCreator.createNewBoard(81),
                "Should throw exception for more than 80 prefilled fields");
    }

    @Test
    void testCreateNewBoardCallsIsSolvableUntilValid() {
        // Arrange
        SudokuCreator sudokuCreator = new SudokuCreator(sudokuSolver);
        int numberOfPrefilledFields = 5;

        // Mock the behavior of isSolvable to return false initially, then true
        when(sudokuSolver.isSolvable(any(int[][].class)))
                .thenReturn(false, false, true); // First 2 calls return false, 3rd call returns true

        // Act
        int[][] board = sudokuCreator.createNewBoard(numberOfPrefilledFields);

        // Assert
        assertNotNull(board, "The board should not be null");

        // Verify the mock was called multiple times
        verify(sudokuSolver, atLeast(3)).isSolvable(any(int[][].class));
    }

    private int countFilledFields(int[][] board) {
        int count = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell != 0) {
                    count++;
                }
            }
        }
        return count;
    }
}