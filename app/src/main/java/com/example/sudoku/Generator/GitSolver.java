package com.example.sudoku.Generator;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.N)
public class GitSolver {

    private static final int EMPTY = 0;
    private final int[] values;


    public GitSolver() {
        this.values = generateRandomValues();
    }

    /**
     * Solves a given {@link GitGrid} using backtracking.
     *
     * @param grid the {@link GitGrid} to solve
     * @throws IllegalStateException in case the provided {@link GitGrid} is invalid.
     */
    public void solve(GitGrid grid) {
        boolean solvable = solve(grid, grid.getFirstEmptyCell());

        if (!solvable) {
            throw new IllegalStateException("The provided grid is not solvable.");
        }
    }

    private boolean solve(GitGrid grid, Optional<GitGrid.Cell> cell) {
        if (!cell.isPresent()) {
            return true;
        }

        for (int value : values) {
            if (grid.isValidValueForCell(cell.get(), value)) {
                cell.get().setValue(value);
                if (solve(grid, grid.getNextEmptyCellOf(cell.get()))) return true;
                cell.get().setValue(EMPTY);
            }
        }

        return false;
    }

    private int[] generateRandomValues() {
        int[] values = { EMPTY, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        Random random = new Random();
        for (int i = 0, j = random.nextInt(9), tmp = values[j]; i < values.length;
             i++, j = random.nextInt(9), tmp = values[j]) {
            if(i == j) continue;

            values[j] = values[i];
            values[i] = tmp;
        }

        return values;
    }
}