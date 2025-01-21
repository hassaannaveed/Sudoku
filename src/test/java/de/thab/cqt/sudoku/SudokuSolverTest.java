package de.thab.cqt.sudoku;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {
    @Test
    void emptyboard(){
        SudokuSolver s = new SudokuSolver();
        int[][] b = new int[9][9];
        boolean actual = s.isValidSudokuBoard(b);
        assertTrue(actual);
    }
    @Test
    void invalidboard(){
        SudokuSolver s = new SudokuSolver();
        int[][] b = new int[5][9];
        boolean actual = s.isValidSudokuBoard(b);
        assertTrue(actual);
    }

    @Test
    void notsolvableboard(){
        SudokuSolver s = new SudokuSolver();
        int[][] b = {
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9}
        };
        boolean actual = s.isSolvable(b);
        assertTrue(actual);
    }

    @Test
    void solvedboard(){
        SudokuSolver s = new SudokuSolver();
        int[][] b = {
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9}
        };
        boolean actual = s.isSolved(b);
        assertTrue(actual);
    }

}
