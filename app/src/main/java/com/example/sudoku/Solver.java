package com.example.sudoku;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

public class Solver {

    private int selectedRow;
    private int selectedColumn;
    private int[][] sudoku;

    public Solver() {
        this.selectedColumn = -1;
        this.selectedRow = -1;
        this.sudoku = new int[9][9];
        for (int i =0; i<9; i++)
        {
            for (int j =0; j<9; j++)
            {
                sudoku[i][j] = 0;
            }
        }
    }


    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

    public boolean isSelected() {
        return (this.selectedColumn != -1 && this.selectedColumn != -1);
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public void setNumber(int num) {
        if (this.isSelected())
        {
            if (this.sudoku[this.selectedColumn-1][this.selectedRow-1] == num)
            {
                this.sudoku[this.selectedColumn-1][this.selectedRow-1] = 0;
            }
            else
            {
                this.sudoku[this.selectedColumn-1][this.selectedRow-1] = num;
            }
        }
    }

    public int[][] getSudoku()
    {
        return this.sudoku;
    }

    public int[][] SolveThisSudoku() {
        //Just to distinct this from everything else. This is just constructing the grid
        Cell grid[][] = new Cell[9][9];
        int[][] returnSudoku = new int[9][9];
        int x = 0; // number of iterations needed
        int ECIS = 0; // every cell is solved, flag for when the while loop to end
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell(this.sudoku[i][j]);
            }

        /*for (int i = 0; i < 9; i++)             //Fill the Sudoku with the given values
            for (int j = 0; j < 9; j++) {
                grid[i][j].setValue(this.sudoku[i][j]);     totalno bespotrebno...
            }*/


        for (x = 0 ; x < 999; x++) {
            if (ECIS == 81) {
                break;
            }
            int index, cubex, cubey;

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grid[i][j].isGiven() && !grid[i][j].isTCHBS()) {
                        cubex = i / 3;        //determining the 3x3 cube
                        cubex *= 3;
                        cubey = j / 3;
                        cubey *= 3;

                        index = grid[i][j].getValue();         //the value of the cell

                        for (int a = cubex; a < cubex + 3; a++)     //removing the possibility of the value being in the 3x3 Cube
                            for (int b = cubey; b < cubey + 3; b++) {
                                grid[a][b].cantBe(index);
                            }

                        for (int k = 0; k < 9; k++)     // removing the possibility of the value being in the same column/row
                        {
                            grid[i][k].cantBe(index);
                            grid[k][j].cantBe(index);
                        }
                        grid[i][j].setTCHBS();
                        ++ECIS;
                    }

                }
            }

            int square = 0;
            long OPH = 0;  //Only possible here (indexes of where X is posible), we know X is going to be in this block and this row/column so remove the possibility of X being in the other block's row/column
            int m1, m2, m3, m11, m22, m33, row = 0, column = 0;

            for (int num = 0; num < 9; num++) {  // very possible value in Sudoku 1-9
                for (int ii = 0; ii < 3; ii++) {
                    for (int jj = 0; jj < 3; jj++) {

                        square = 0;
                        OPH = 0;

                        for (int i = 0; i < 3; i++) {        // For loops to see if there can be the number X only in one cell in a block
                            for (int j = 0; j < 3; j++) {   // OR if X is in one row or column in that same block so that we can remove the possibility of X
                                if (grid[i + (3 * ii)][j + (3 * jj)].IsThisPossibleValue(num) && !grid[i + (3 * ii)][j + (3 * jj)].isGiven()) {   // being in that row/column in the other blocks
                                    square++;
                                    row = i + (3 * ii);
                                    column = j + (3 * jj);

                                    OPH *= 100;
                                    OPH += (i + (3 * ii) + 1) * 10 + j + (3 * jj) + 1;
                                }
                            }
                        }
                        if (square == 1) {
                            grid[row][column].setValue(num + 1);
                            continue;
                        }

                        //moze da javi graska za nekoi so se vekje vo kockata


                        if (OPH > 999999 || OPH < 1) {
                            continue;
                        } else {                      //Where in the block is X possible
                            m1 = (int) OPH % 100;   //Index 1 where X is possible
                            OPH /= 100;
                            m2 = (int) OPH % 100;   //Index 2 where X is possible
                            OPH /= 100;
                            m3 = (int) OPH % 100;   //Index 3 where X is possible
                            OPH /= 100;

                            if (m3 == 0)        //If X is possible only in 2 cells, make index 3 = index 2 so we dont have to write separate code
                            {
                                m3 = m2;
                            }

                            m11 = m1 % 10;  // j/column from index 1
                            m1 /= 10;       // i/row from index 1
                            m22 = m2 % 10;  // j/column from index 2
                            m2 /= 10;       // i/row from index 2
                            m33 = m3 % 10;  // j/column from index 3
                            m3 /= 10;       // i/row from index 3


                            if (m1 == m2 && m2 == m3) {   // X is in this block and this row
                                if (m33 < 4) {
                                    for (int o = 3; o < 9; o++) {   // X is somewhere in the first three columns, cant be in the others
                                        grid[m1 - 1][o].cantBe(num + 1);
                                    }
                                } else if (m33 > 6) {    // X is somewhere in the last three columns, cant be in the others
                                    for (int o = 0; o < 6; o++) {
                                        grid[m1 - 1][o].cantBe(num + 1);
                                    }
                                } else {            // X is in the fourth, fifth or sixth column, cant be in the others
                                    for (int o = 0; o < 3; o++) {
                                        grid[m1 - 1][o].cantBe(num + 1);
                                    }
                                    for (int o = 6; o < 9; o++) {
                                        grid[m1 - 1][o].cantBe(num + 1);
                                    }
                                }
                            } else if (m11 == m22 && m22 == m33) {     // X is in this block and this column
                                if (m1 < 4) {
                                    for (int o = 3; o < 9; o++) {       // X is somewhere in the first three rows, cant be in the others
                                        grid[o][m33 - 1].cantBe(1 + num);
                                    }
                                } else if (m3 > 6) {
                                    for (int o = 0; o < 6; o++) {       // X is somewhere in the last three rows, cant be in the others
                                        grid[o][m33 - 1].cantBe(num + 1);
                                    }
                                } else {        // X is in the fourth, fifth or sixth row, cant be in the others
                                    for (int o = 0; o < 3; o++) {
                                        grid[o][m33 - 1].cantBe(num + 1);
                                    }
                                    for (int o = 6; o < 9; o++) {
                                        grid[o][m33 - 1].cantBe(num + 1);
                                    }
                                }
                            }

                        }
                    }
                }

            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (!grid[i][j].isGiven()) {
                        grid[i][j].checkPossible();
                    }
                }
            }

        }

        String s = "\n ";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    s += "\t";
                }
                s += " ";
                s += grid[i][j].getValue();  //something something about how the for loops are done and the sudoku is printed transposed
                s += " ";

                //this.sudoku[i][j] = grid[i][j].getValue();
                returnSudoku[i][j]  = grid[i][j].getValue();
            }
            s += "\n";
            if ((i + 1) % 3 == 0) {
                s += "\n";
            }
        }
        Log.d("Result", s);
        Log.d("Number of solved cells", Integer.toString(ECIS));
        Log.d("Iterations", Integer.toString(x));

        return returnSudoku;
    }

    public void Solve()
    {
        this.sudoku = SolveThisSudoku();
    }

    public boolean HintCell()
    {
        if (this.selectedColumn == -1 && this.selectedRow == -1)
        {
            return false;
        }

        int[][] hinting = SolveThisSudoku();
        this.sudoku[this.selectedColumn-1][this.selectedRow-1] = hinting[this.selectedColumn-1][this.selectedRow-1];

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean Check() {
        int[][] n = this.sudoku;
        int s = Arrays.stream(n).filter(i -> (Arrays.stream(i).sum() == 45)).map(i -> i).mapToInt(i -> Arrays.stream(i).sum()).sum();       //logikava mi raboti ama namesto redici presmetuva za koloni
        Log.d("CHECK", String.valueOf(s));
        return true;
    }
}
