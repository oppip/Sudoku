package com.example.sudoku;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SolveSudoku extends AppCompatActivity {

    private SudokuGrid sudokuGrid;
    private Solver solver;
    private Button solveButton, hintButton;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku);
        sudokuGrid = findViewById(R.id.SudokuGrid);
        solver = sudokuGrid.getSolver();
        solveButton = findViewById(R.id.SolveButton);
        hintButton = findViewById(R.id.HintButton);

        hintButton.setBackgroundColor(getColor(R.color.SelectedCell));
        solveButton.setBackgroundColor(getColor(R.color.SolvedCell));

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solveButton.getText().toString().equals(getString(R.string.Solve)))
                {
                    solveButton.setText(R.string.Clear);
                    solveButton.setBackgroundColor(getColor(R.color.SelectedCell));
                    solver.Solve();
                    sudokuGrid.invalidate();
                }
                else
                {
                    solveButton.setText(R.string.Solve);
                    solveButton.setBackgroundColor(getColor(R.color.SolvedCell));
                    solver = new Solver();
                    sudokuGrid.setSolver(solver);
                    sudokuGrid.invalidate();
                    //Toast.makeText(getApplicationContext(), "Good luck!", Toast.LENGTH_LONG).show();
                }
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solver.Check();
            }
        });

    }

    public void ButtonOnePressed(View view)
    {
        solver.setNumber(1);
        sudokuGrid.invalidate();
    }
    public void ButtonTwoPressed(View view)
    {
        solver.setNumber(2);
        sudokuGrid.invalidate();
    }
    public void ButtonThreePressed(View view)
    {
        solver.setNumber(3);
        sudokuGrid.invalidate();
    }
    public void ButtonFourPressed(View view)
    {
        solver.setNumber(4);
        sudokuGrid.invalidate();
    }
    public void ButtonFivePressed(View view)
    {
        solver.setNumber(5);
        sudokuGrid.invalidate();
    }
    public void ButtonSixPressed(View view)
    {
        solver.setNumber(6);
        sudokuGrid.invalidate();
    }
    public void ButtonSevenPressed(View view)
    {
        solver.setNumber(7);
        sudokuGrid.invalidate();
    }
    public void ButtonEightPressed(View view)
    {
        solver.setNumber(8);
        sudokuGrid.invalidate();
    }
    public void ButtonNinePressed(View view)
    {
        solver.setNumber(9);
        sudokuGrid.invalidate();
    }

}