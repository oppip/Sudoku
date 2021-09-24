package com.example.sudoku.Generator;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.sudoku.Solver;

import java.util.Arrays;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Generate {
    private GitSolver gitSolver;

    /**
     * Constructs a new Generator instance.
     */

    public Generate() {
        this.gitSolver = new GitSolver();
    }

    public int[][] generate(int numberOfEmptyCells) {
        GitGrid grid = generate();
        eraseCells(grid, numberOfEmptyCells);

        return Solvable(grid.getMatrix(), numberOfEmptyCells);
    }

    private int[][] Solvable(int[][] sudoku, int numberOfEmptyCells)
    {
        Solver solver = new Solver();
        solver.setSudoku(sudoku);
        int n = Arrays.stream(solver.SolveThisSudoku()).mapToInt(i -> Arrays.stream(i).sum()).sum();

        if (n == 405)
        {
            return sudoku;
        }
        return generate(numberOfEmptyCells);
    }

    private void eraseCells(GitGrid grid, int numberOfEmptyCells) {
        Random random = new Random();
        for (int i = 0; i < numberOfEmptyCells; i++) {
            int randomRow = random.nextInt(9);
            int randomColumn = random.nextInt(9);

            GitGrid.Cell cell = grid.getCell(randomRow, randomColumn);
            if (!cell.isEmpty()) {
                cell.setValue(0);
            } else {
                i--;
            }
        }
    }

    public GitGrid generate() {
        GitGrid grid = GitGrid.emptyGrid();

        gitSolver.solve(grid);

        return grid;
    }
}