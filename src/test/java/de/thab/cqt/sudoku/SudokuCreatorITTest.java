package de.thab.cqt.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuIntegrationTest {

    @Test
    void testCreateAndValidateBoard() {
        // Arrange
        SudokuSolver sudokuSolver = new SudokuSolver();
        SudokuCreator sudokuCreator = new SudokuCreator(sudokuSolver);

        int numberOfPrefilledFields = 30;

        // Act
        int[][] board = sudokuCreator.createNewBoard(numberOfPrefilledFields);

        // Assert
        assertTrue(sudokuSolver.isValidSudokuBoard(board), "The board should have valid dimensions and structure.");
        assertEquals(numberOfPrefilledFields, countPrefilledFields(board), "The board should have the correct number of prefilled fields.");
        assertTrue(sudokuSolver.isSolvable(board), "The board should be solvable.");
    }

    @Test
    void testBoardSolvabilityWithEdgeCases() {
        // Arrange
        SudokuSolver sudokuSolver = new SudokuSolver();
        SudokuCreator sudokuCreator = new SudokuCreator(sudokuSolver);

        // Test with minimum valid prefilled fields
        int[][] minPrefilledBoard = sudokuCreator.createNewBoard(1);
        assertTrue(sudokuSolver.isValidSudokuBoard(minPrefilledBoard), "Board with minimum prefilled fields should be valid.");
        assertTrue(sudokuSolver.isSolvable(minPrefilledBoard), "Board with minimum prefilled fields should be solvable.");

        // Test with maximum valid prefilled fields
        int[][] maxPrefilledBoard = sudokuCreator.createNewBoard(80);
        assertTrue(sudokuSolver.isValidSudokuBoard(maxPrefilledBoard), "Board with maximum prefilled fields should be valid.");
        assertTrue(sudokuSolver.isSolvable(maxPrefilledBoard), "Board with maximum prefilled fields should be solvable.");
    }

    @Test
    void testInvalidNumberOfPrefilledFields() {
        // Arrange
        SudokuSolver sudokuSolver = new SudokuSolver();
        SudokuCreator sudokuCreator = new SudokuCreator(sudokuSolver);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> sudokuCreator.createNewBoard(0), "Should throw exception for less than minimum prefilled fields.");
        assertThrows(IllegalArgumentException.class, () -> sudokuCreator.createNewBoard(81), "Should throw exception for more than maximum prefilled fields.");
    }

    // Helper method to count prefilled fields in a board
    private int countPrefilledFields(int[][] board) {
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