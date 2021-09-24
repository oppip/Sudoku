package com.example.sudoku;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.sudoku", appContext.getPackageName());
    }

    @Test
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
        solver.Solve();
        assertEquals(solved, solver.getSudoku());
    }

    @Test
    public void sudoku_medium() {
        int[][] solved = { {1, 5, 9, 6, 2, 8, 3, 7, 4}, { 8, 3, 6, 4, 5, 7, 2, 1, 9 }, {7, 4, 2, 9, 3, 1, 8, 6, 5},
                {6, 7, 1, 2, 4, 9, 5, 3, 8}, { 2, 8, 3, 5, 1, 6, 4, 9, 7 }, {5, 9, 4, 8, 7, 3, 1, 2, 6},
                {4, 6, 7, 1, 8, 2, 9, 5, 3}, { 9, 1, 5, 3, 6, 4, 7, 8, 2 }, {3, 2, 8, 7, 9, 5, 6, 4, 1}
        };
        int[][] not_solved = { {0, 5, 0, 0, 2, 8, 0, 7, 0}, { 8, 0, 6, 4, 5, 0, 0, 0, 9 }, {0, 0, 0, 0, 0, 1, 0, 0, 5},
                {6, 7, 0, 0, 0, 0, 0, 0, 8}, { 0, 8, 3, 0, 1, 6, 0, 0, 0 }, {0, 0, 0, 8, 0, 3, 0, 2, 6},
                {0, 0, 7, 0, 0, 0, 9, 5, 0}, { 0, 0, 5, 0, 0, 0, 0, 0, 0 }, {3, 2, 8, 7, 9, 5, 0, 0, 0}
        };
        Solver solver = new Solver();
        solver.setSudoku(not_solved);
        solver.Solve();
        assertEquals(solved, solver.getSudoku());
    }

    @Test
    public void sudoku_hard() {
        int[][] solved = { {1, 2, 3, 4, 9, 7, 6, 8, 5}, { 7, 4, 6, 5, 8, 3, 2, 1, 9 }, {5, 8, 9, 1, 2, 6, 7, 3, 4},
                {4, 7, 2, 9, 3, 1, 5, 6, 8}, { 9, 5, 1, 6, 7, 8, 3, 4, 2 }, {6, 3, 8, 2, 4, 5, 1, 9, 7},
                {8, 1, 7, 3, 5, 4, 9, 2, 6}, { 3, 9, 4, 7, 6, 2, 8, 5, 1 }, {2, 6, 5, 8, 1, 9, 4, 7, 3}
        };
        int[][] not_solved = { {0, 2, 0, 0, 0, 0, 6, 8, 0}, { 0, 4, 0, 5, 0, 0, 0, 1, 0 }, {5, 0, 0, 1, 0, 0, 7, 3, 0},
                {4, 0, 0, 9, 0, 1, 5, 0, 0}, { 0, 5, 0, 6, 0, 8, 0, 0, 2 }, {0, 0, 8, 2, 0, 5, 0, 0, 0},
                {0, 1, 0, 0, 0, 4, 0, 0, 6}, { 3, 0, 0, 0, 0, 2, 0, 5, 0 }, {0, 0, 0, 8, 0, 0, 0, 7, 0}
        };
        Solver solver = new Solver();
        solver.setSudoku(not_solved);
        solver.Solve();
        assertEquals(solved, solver.getSudoku());
    }

    @Test
    public void sudoku_hard2() {
        int[][] solved = { {5, 7, 2, 1, 8, 6, 4, 3, 9}, { 8, 9, 3, 4, 7, 2, 1, 5, 6 }, {1, 4, 6, 9, 3, 5, 2, 8, 7},
                {4, 3, 9, 6, 1, 8, 7, 2, 5}, { 6, 2, 5, 7, 9, 3, 8, 1, 4 }, {7, 8, 1, 2, 5, 4, 6, 9, 3},
                {2, 5, 7, 8, 6, 9, 3, 4, 1}, { 3, 1, 4, 5, 2, 7, 9, 6, 8 }, {9, 6, 8, 3, 4, 1, 5, 7, 2}
        };
        int[][] not_solved = { {5, 0, 2, 0, 0, 0, 0, 3, 0}, { 0, 0, 3, 4, 7, 0, 1, 0, 0 }, {0, 0, 0, 0, 0, 5, 0, 0, 0},
                {4, 3, 0, 0, 0, 8, 7, 0, 0}, { 6, 0, 0, 0, 0, 0, 0, 0, 4 }, {0, 0, 1, 2, 0, 0, 0, 9, 3},
                {0, 0, 0, 8, 0, 0, 0, 0, 0}, { 0, 0, 4, 0, 2, 7, 9, 0, 0 }, {0, 6, 0, 0/*3 ako sakas da raboti*/, 0, 0, 5, 0, 2}
        };
        Solver solver = new Solver();
        solver.setSudoku(not_solved);
        solver.Solve();
        assertEquals(solved, solver.getSudoku());
    }

}