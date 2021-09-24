package com.example.sudoku;

public class Cell {

    private int realVlaue;
    private boolean possibleValues[] = new boolean[9];
    private boolean given;
    private boolean TCHBS; // this cell has been solved, meaning that the algorythm already removed the posibilities in the row, column and block

    public Cell() {
        this.realVlaue = -1;
        this.given = false;
        this.TCHBS = false;
        for (int i = 0; i < 9; i++) {
            this.possibleValues[i] = true;
        }
    }

    public Cell(int realVlaue) {
        this.realVlaue = realVlaue;
        this.given = false;
        this.TCHBS = false;
        if (realVlaue != 0) {
            this.given = true;
        }
        for (int i = 0; i < 9; i++) {
            this.possibleValues[i] = true;
        }
    }

    public boolean isGiven() {
        return given;
    }

    public boolean IsThisPossibleValue(int i) {
        return this.possibleValues[i];
    }

    public void setValue(int n) {
        this.realVlaue = n;
        if (n != 0) {
            this.given = true;
        }
    }

    public boolean isTCHBS() {
        return TCHBS;
    }

    public void setTCHBS() {
        this.TCHBS = true;
    }

    public void removeValue(int n) {
        this.realVlaue = -1;
        this.given = false;
    }

    public int getValue() {
        return this.realVlaue;
    }

    public void cantBe(int n) {
        this.possibleValues[n - 1] = false;
    }

    public void checkPossible() {
        int is = 0, br = 0;
        for (int i = 0; i < 9; i++) {
            if (this.possibleValues[i]) {
                is = i;
                br++;
            }
        }
        if (br == 1) {
            setValue(is + 1);
        }
    }

}
