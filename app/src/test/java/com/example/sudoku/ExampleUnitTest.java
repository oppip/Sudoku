package com.example.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*@Test
    public void sudoku_easy() {
        int[][] solved = { {2, 6, 5, 7, 4, 9, 1, 8, 3}, { 9, 1, 3, 5, 8, 2, 4, 7, 6 }, {4, 7, 8, 1, 3, 6, 9, 2, 5},
                      {8, 3, 2, 4, 6, 1, 7, 5, 9}, { 5, 4, 6, 8, 9, 7, 3, 1, 2 }, {7, 9, 1, 2, 5, 3, 6, 4, 8},
                      {3, 2, 4, 9, 7, 8, 5, 6, 1}, { 6, 8, 7, 3, 1, 5, 2, 9, 4 }, {1, 5, 9, 6, 2, 4, 8, 3, 7}
        };
        int[][] not_solved = { {0, 0, 0, 0, 4, 9, 0, 8, 3}, { 0, 1, 0, 5, 0, 0, 0, 0, 0 }, {0, 0, 8, 0, 0, 0, 9, 0, 5},
                {8, 0, 2, 4, 6, 1, 7, 0, 9}, { 5, 0, 6, 8, 9, 0, 3, 1, 2 }, {7, 0, 1, 0, 0, 0, 0, 4, 0},
                {0, 2, 4, 9, 7, 0, 0, 0, 0}, { 0, 8, 0, 0, 1, 5, 2, 0, 0 }, {0, 0, 9, 0, 0, 0, 0, 3, 0}
        };
        Solver solver = new Solver();
        solver.setSudoku(not_solved);
        solver.SolveThisSudoku();
        assertEquals(solved, solver.getSudoku());
    }*/



}